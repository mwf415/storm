package com.youyicn.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;

public class SolrUtil {
	private static Logger log = Logger.getLogger(SolrUtil.class);

	private static String URL = "";
	private static SolrUtil solrContext = new SolrUtil();
	private static HttpSolrServer solrServer = new HttpSolrServer(URL);

	static {
		solrServer.setSoTimeout(100000); // socket read timeout
		solrServer.setConnectionTimeout(100000);
		solrServer.setDefaultMaxConnectionsPerHost(100);
		solrServer.setMaxTotalConnections(100);
		solrServer.setFollowRedirects(false); // defaults to false
		solrServer.setAllowCompression(true);
		solrServer.setMaxRetries(1); // defaults to 0. > 1 not recommended.
	}

	private SolrUtil() {

		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream("config.properties");
		try {
			Properties properties = new Properties();
			properties.load(inputStream);
			URL = properties.getProperty("SOLR_URL");
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	public static SolrUtil getInstance() {
		return solrContext;
	}

	public static HttpSolrServer getServer() {
		return solrServer;
	}

	/**
	 * @Description:为多个文档对象的，某些属性建立索引
	 * @fieldName: list集合对象 properties 对象属性
	 * @return: void
	 */
	public static <T> void addDocs(List<T> list, List<String> properties) {
		if (null == list || list.size() == 0) {
			return;
		}
		List<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
		List<Field> fields = ReflectUtils.getAllFields(list.get(0).getClass());
		for (T obj : list) {
			SolrInputDocument doc = new SolrInputDocument();
			for (Field field : fields) {
				for (String property : properties) {
					if (property.equals(field.getName())) {
						doc.addField(property,
								ReflectUtils.invokeGetterMethod(obj, property));
					}
				}
			}
			docs.add(doc);
		}
		try {
			solrServer.add(docs);
			solrServer.commit(false, false);
		} catch (Exception e) {
			log.info("添加索引失败！");
			e.printStackTrace();
		}
	}

	/**
	 * @Description:建立单个索引
	 * @fieldName: 
	 * @return: void
	 */
	public static <T> void addDoc(T obj, List<String> properties) {
		List<T> list = new ArrayList<T>();
		list.add(obj);
		addDocs(list, properties);
	}
	
	/**
	 * @Description:将整个对象都添加到索引
	 * @fieldName: 
	 * @return: void
	 */
	public static <T> void addBean(T obj) {
		try {
			solrServer.addBean(obj);
			solrServer.commit(false, false);
		} catch (Exception e) {
			log.info("添加索引失败！");
			e.printStackTrace();
		}
	}

	/**
	 * 添加多个索引对象
	 * 
	 * @param <T>
	 * @date 2015-8-30 下午5:36:37
	 * @param students
	 */
	public static <T> void addBeans(List<T> list) {
		try {
			solrServer.addBeans(list);
			solrServer.commit(false, false);
		} catch (Exception e) {
			log.info("添加索引失败！");
			e.printStackTrace();
		}
	}

	/**
	 * @Description: 根据id删除某条索引
	 * @fieldName:
	 * @return: void
	 */
	public static void deleteById(String id) {
		try {
			solrServer.deleteById(id);
			solrServer.commit(false, false);
		} catch (Exception e) {
			log.info("删除索引失败！");
			e.printStackTrace();
		}
	}

	public static <E> void deleteByIds(List<E> ids) {
		if (ids.size() > 0) {
			StringBuffer query = new StringBuffer("id:(");
			for (int i = 0; i < ids.size(); i++) {
				if (null != ids.get(i)) {
					query.append(ids.get(i).toString()+" ");
				}
			}
			query.append(")");
			log.info("删除索引"+query.toString());
			try {
				solrServer.deleteByQuery(query.toString());
				solrServer.commit(false, false);
			} catch (SolrServerException | IOException e) {
				log.info("删除索引失败！");
				e.printStackTrace();
			}
		}
	}

	/**
	 * @Description:根据查询语句删除索引
	 * @fieldName:
	 * @return: void
	 */
	public static void deleteByQuery(String query) {
		try {
			solrServer.deleteByQuery(query);
			solrServer.commit(false, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static <T, E> T queryById(Class<E> id, Class<T> entityClass) {
		T obj = null;
		SolrQuery query = new SolrQuery();
		query.setQuery("id:" + id.toString());
		QueryResponse response = null;
		try {
			obj = entityClass.newInstance();
			response = solrServer.query(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		SolrDocumentList docs = response.getResults();
		if (null == docs || docs.size() == 0) {
			return null;
		}
		SolrDocument doc = docs.get(0);
		List<Field> fields = ReflectUtils.getAllFields(obj.getClass());
		for (Field field : fields) {
			String propertyName = field.getName();
			Class<?> propertyClass = field.getType();
			Object property = doc.getFieldValue(propertyName);
			String propertyValue;
			if (property == null) {
				continue;
			} else {
				propertyValue = property.toString();
			}
			propertyValue = propertyValue.replaceAll("\\[", "").replaceAll(
					"\\]", "");
			ReflectUtils.invokeSetterMethod(obj, propertyClass, propertyName,
					propertyValue);
		}

		return obj;
	}

	/**
	 * @param <E>
	 * @Description:根据多个id从solr获取java对象
	 * @fieldName:
	 * @return: List<T>
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static <T, E> List<T> queryByIds(List<E> ids, Class<T> entityClass){
		List<T> list = Lists.newArrayList();
		StringBuffer query = new StringBuffer("id:(");
		if (ids.size() > 0) {
			for (int i = 0; i < ids.size(); i++) {
				if (null != ids.get(i)) {
					query.append(ids.get(i).toString()+" ");
				}
			}
			query.append(")");
		}
		
		QueryResponse response = null;
		try {
			response = solrServer.query(new SolrQuery(query.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		SolrDocumentList docs = response.getResults();
		if (null == docs || docs.size() == 0) {
			return null;
		}
		for(SolrDocument doc: docs){
			T obj = null;
			try {
				obj = entityClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			List<Field> fields = ReflectUtils.getAllFields(obj.getClass());
			for (Field field : fields) {
				String propertyName = field.getName();
				Class<?> propertyClass = field.getType();
				Object property = doc.getFieldValue(propertyName);
				String propertyValue;
				if (property == null) {
					continue;
				} else {
					propertyValue = property.toString();
				}
				propertyValue = propertyValue.replaceAll("\\[", "").replaceAll(
						"\\]", "");
				ReflectUtils.invokeSetterMethod(obj, propertyClass, propertyName,
						propertyValue);
			}
			list.add(obj);
		}
		return list;
	}

	/**
	 * @Description:solr获取的分页对象
	 * @param page
	 * @param solrQuery 里面封装了查询对象的条件，如果想查询全部数据，pageSize设置足够大
	 * @return
	 */
	@Deprecated
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> PageBean getPage(PageBean page, SolrQuery solrQuery,
			Class<T> entityClass) {
		solrQuery.setStart(page.getPageNum());// 开始索引
		solrQuery.setRows(page.getPageSize());// 分页的数量
		QueryResponse queryResponse = null;
		try {
			queryResponse = solrServer.query(solrQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		SolrDocumentList docs = queryResponse.getResults();
		List<T> list = new ArrayList<T>();

		for (SolrDocument doc : docs) {
			T obj = null;
			try {
				obj = entityClass.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<Field> fields = ReflectUtils.getAllFields(obj.getClass());
			for (Field field : fields) {
				String propertyName = field.getName();
				Class<?> propertyClass = field.getType();
				Object property = doc.getFieldValue(propertyName);
				String propertyValue;
				if (property == null) {
					continue;
				} else {
					propertyValue = property.toString();
				}
				propertyValue = propertyValue.replaceAll("\\[", "").replaceAll(
						"\\]", "");
				ReflectUtils.invokeSetterMethod(obj, propertyClass,
						propertyName, propertyValue);
			}
			list.add(obj);
		}
		page.setTotal(docs.size());
		page.setList(list);
		return page;
	}
	/**
	 * @Description:根据solrQuery分页查询
	 * @fieldName: 
	 * @return: PageInfo<T>
	 */
	public static <T> PageInfo<T> getPageBySolrQuery(Integer pageNum,Integer pageSize, SolrQuery solrQuery,
			Class<T> entityClass) {
		if(pageNum==null || pageNum<1) pageNum=1;
		if(pageSize==null || pageSize<0) pageSize=20;
		solrQuery.setStart(pageNum);// 开始索引
		solrQuery.setRows(pageSize);// 分页的数量
		QueryResponse queryResponse = null;
		try {
			queryResponse = solrServer.query(solrQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		SolrDocumentList docs = queryResponse.getResults();
		List<T> list = new ArrayList<T>();

		for (SolrDocument doc : docs) {
			T obj = null;
			try {
				obj = entityClass.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<Field> fields = ReflectUtils.getAllFields(obj.getClass());
			for (Field field : fields) {
				String propertyName = field.getName();
				Class<?> propertyClass = field.getType();
				Object property = doc.getFieldValue(propertyName);
				String propertyValue;
				if (property == null) {
					continue;
				} else {
					propertyValue = property.toString();
				}
				propertyValue = propertyValue.replaceAll("\\[", "").replaceAll(
						"\\]", "");
				ReflectUtils.invokeSetterMethod(obj, propertyClass,
						propertyName, propertyValue);
			}
			list.add(obj);
		}
		
		Page<T> page = new Page<T>();
        page.setTotal(list.size());
        page.addAll(list);
        return new PageInfo<T>(page);  
	}
	
	/**
	 * @Description: 根据solrQuery条件查询
	 * @fieldName: 
	 * @return: List<T>
	 */
	public static <T> List<T> getAllBySolrQuery(SolrQuery solrQuery,
			Class<T> entityClass) {
		QueryResponse queryResponse = null;
		try {
			queryResponse = solrServer.query(solrQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		SolrDocumentList docs = queryResponse.getResults();
		List<T> list = new ArrayList<T>();
		for (SolrDocument doc : docs) {
			T obj = null;
			try {
				obj = entityClass.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<Field> fields = ReflectUtils.getAllFields(obj.getClass());
			for (Field field : fields) {
				String propertyName = field.getName();
				Class<?> propertyClass = field.getType();
				Object property = doc.getFieldValue(propertyName);
				String propertyValue;
				if (property == null) {
					continue;
				} else {
					propertyValue = property.toString();
				}
				propertyValue = propertyValue.replaceAll("\\[", "").replaceAll(
						"\\]", "");
				ReflectUtils.invokeSetterMethod(obj, propertyClass,
						propertyName, propertyValue);
			}
			list.add(obj);
		}
		return list;
	}
	
	/**
	 * @return 
	 * @Description:统计 Count name名字，count数量
	 * @fieldName: 
	 * @return: void
	 */
	public static List<Count> Facet(SolrQuery solrQuery){
		QueryResponse response;
		List<Count> counts = null;
		try {
			response = solrServer.query(solrQuery);
			List<FacetField> facets = response.getFacetFields();//返回的facet列表
			FacetField facet = facets.get(0);
			counts = facet.getValues();
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		return counts;
	}
	
	/**
	 * @Description:优化solr索引
	 * @fieldName:
	 * @return: void
	 */
	public static void optimize(String collection) {
		try {
			solrServer.optimize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * @Description:增量/全量建立索引 。
	 * @fieldName:
	 * @return: void
	 */
	public static void rebuildIndex(boolean delta) {
		deleteByQuery("*:*");
		SolrQuery query = new SolrQuery();
		// 指定RequestHandler，默认使用/select
		query.setRequestHandler("/dataimport");
		delta = false;
		String command = delta ? "delta-import" : "full-import";
		String clean = delta ? "false" : "true";
		String optimize = delta ? "false" : "true";

		query.setParam("command", command).setParam("commit", "true");
		// .setParam("optimize", optimize);
		try {
			solrServer.query(query);
			log.info("创建索引成功！");
			System.out.println("创建索引成功！");
		} catch (SolrServerException e) {
			log.error("建立索引时遇到错误，delta:" + delta, e);
		}
	}
	
	/**
	 * @Description:删除索引索引
	 * @fieldName: 
	 * @return: void
	 */
    public void deleteAll(){  
    	try {
			solrServer.deleteByQuery("*:*");
			solrServer.commit(false, false);  
		} catch (SolrServerException | IOException e) {
			log.info("删除索引失败！");
			e.printStackTrace();
		}  
    } 
    
}
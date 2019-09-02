package com.youyicn.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
  
/** 
 * @Description:单例的solr服务工具类 
 * @author kang 
 * @创建时间 2015下午2:17:18 
 */  
public class SolrUtils  
{  
    private static String URL = "";  
    private static SolrUtils solrContext = new SolrUtils();  
    private static HttpSolrServer server = new HttpSolrServer(URL);  
  
    static  
    {  
        server.setSoTimeout(100000); // socket read timeout  
        server.setConnectionTimeout(100000);  
        server.setDefaultMaxConnectionsPerHost(100);  
        server.setMaxTotalConnections(100);  
        server.setFollowRedirects(false); // defaults to false  
        server.setAllowCompression(true);  
        server.setMaxRetries(1); // defaults to 0. > 1 not recommended.  
    }  
  
    private SolrUtils()  
    {  
  
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("config.properties");  
        try  
        {  
            Properties properties = new Properties();  
            properties.load(inputStream);  
            URL = properties.getProperty("SOLR_URL");  
        }  
        catch (IOException e)  
        {  
            throw new RuntimeException(e.getMessage());  
        }  
        finally  
        {  
            try  
            {  
                inputStream.close();  
            }  
            catch (IOException e)  
            {  
                throw new RuntimeException(e.getMessage());  
            }  
        }  
    }  
  
    public static SolrUtils getInstance()  
    {  
        return solrContext;  
    }  
  
    public static HttpSolrServer getServer()  
    {  
        return server;  
    }  
  
    /** 
     * @Description: 添加单个对象到索引 
     * @author kang 
     * @创建时间 2015下午2:59:50 
     * @param @param object 
     * @param @throws IOException 
     * @param @throws SolrServerException 
     * @return void 
     * @throws 
     */  
    public void addBean(Object object) throws IOException, SolrServerException  
    {  
        server.addBean(object);  
        server.commit(false, false);  
    }  
  
    /** 
     * @Description: 添加集合到索引 
     * @author kang 
     * @创建时间 2015下午3:02:23 
     * @param @param lists 
     * @param @throws SolrServerException 
     * @param @throws IOException 
     * @return void 
     * @throws 
     */  
    public <E> void addBeans(List<E> lists) throws SolrServerException, IOException  
    {  
        server.addBeans(lists);  
        server.commit(false, false);  
    }  
  
    /** 
     * @Description: 根据id删除记录 
     * @author kang 
     * @创建时间 2015下午5:10:54 
     * @param @param idName 
     * @param @param id 
     * @param @throws SolrServerException 
     * @param @throws IOException 
     * @return void 
     * @throws 
     */  
    public void deleteById(String idName, Object id) throws SolrServerException, IOException  
    {  
        server.deleteByQuery(idName + ":" + id.toString());  
        server.commit(false, false);  
    }  
  
    /** 
     * @Description: 根据ids批量删除 
     * @author kang 
     * @创建时间 2015下午5:26:39 
     * @param @param idName 
     * @param @param ids 
     * @param @throws SolrServerException 
     * @param @throws IOException 
     * @return void 
     * @throws 
     */  
    public <E> void deleteByIds(String idName, List<E> ids) throws SolrServerException, IOException  
    {  
        if (ids.size() > 0)  
        {  
            StringBuffer query = new StringBuffer(idName + ":" + ids.get(0));  
            for (int i = 1; i < ids.size(); i++)  
            {  
                if (null != ids.get(i))  
                {  
                    query.append(" OR " + idName + ":" + ids.get(i).toString());  
                }  
            }  
            server.deleteByQuery(query.toString());  
            server.commit(false, false);  
        }  
    }  
  
    /** 
     *  
     * @Description: 根据查询从索引删除 
     * @author kang 
     * @创建时间 2015下午5:32:35 
     * @param @param query 
     * @param @throws SolrServerException 
     * @param @throws IOException 
     * @return void 
     * @throws 
     */  
    public void deleteByQuery(String query) throws SolrServerException, IOException  
    {  
        server.deleteByQuery(query);  
        server.commit(false, false);  
    }  
  
    /** 
     *  
     * @Description: 删除所有 
     * @author kang 
     * @创建时间 2015下午5:36:21 
     * @param @throws SolrServerException 
     * @param @throws IOException 
     * @return void 
     * @throws 
     */  
    public void deleteAll() throws SolrServerException, IOException  
    {  
        server.deleteByQuery("*:*");  
        server.commit(false, false);  
    }  
  
    /** 
     * @Description: 关键字分页查询 
     * @author kang 
     * @创建时间 2015下午4:54:24 
     * @param @param keyword 
     * @param @param pageNum 
     * @param @param pageSize 
     * @param @param clzz 
     * @param @return 
     * @return Page<T> 
     * @throws 
     */  
    public <T> PageInfo<T> getByPage(String keywords, int pageNum, int pageSize, Class<T> clzz, String lang, Boolean distinguish)  
    {  
        SolrQuery query = new SolrQuery();  
        query.setQuery(keywords)// 查询内容  
                .setStart((pageNum - 1) * pageSize)// 分页  
                .setRows(pageSize);//  
        if (distinguish)  
        {  
            query.addFilterQuery("lang:" + lang);// 中英文区别  
        }  
        QueryResponse response = null;  
        try  
        {  
            response = server.query(query);  
        }  
        catch (SolrServerException e)  
        {  
            e.printStackTrace();  
            return null;  
        }  
        // 查询结果集  
        SolrDocumentList results = response.getResults();  
        // 获取对象  
        List<T> beans = server.getBinder().getBeans(clzz, results);  
        // 总记录数  
        int total = new Long(response.getResults().getNumFound()).intValue();  
        Page<T> page = new Page<T>();
        page.setTotal(total);
        page.addAll(beans);
        return new PageInfo<T>(page);  
    }  
  
    /** 
     * @Description: 判断是否有索引数据 
     * @author kang 
     * @创建时间 2015下午4:54:24 
     * @param @param keyword 
     * @param @param pageNum 
     * @param @param pageSize 
     * @param @param clzz 
     * @param @return 
     * @return Page<T> 
     * @throws 
     */  
    public boolean hasIndex()  
    {  
        SolrQuery query = new SolrQuery();  
        query.setQuery("*:*")// 查询内容  
                .setStart(0)// 分页  
                .setRows(1);  
        QueryResponse response = null;  
        try  
        {  
            response = server.query(query);  
        }  
        catch (SolrServerException e)  
        {  
            e.printStackTrace();  
            return false;  
        }  
        // 总记录数  
        int total = new Long(response.getResults().getNumFound()).intValue();  
        return total == 0 ? false : true;  
    }  
  
    /** 
     *  
     * @Description:带高亮的关键字查询 
     * @author kang 
     * @创建时间 2015下午9:03:02 
     * @param @param keywords 
     * @param @param pageNum 
     * @param @param pageSize 
     * @param @param hlFields 
     * @param @param preTag 
     * @param @param postTag 
     * @param @param clzz 
     * @param @param idName 
     * @param @return 
     * @return Page<T> 
     * @throws 
     */  
    public <T> PageInfo<T> getHighterByPage(String keywords, int pageNum, int pageSize, List<String> hlFields, String preTag, String postTag, Class<T> clzz, String idName, String lang, Boolean distinguish)  
    {  
        SolrQuery query = new SolrQuery();  
        query.setQuery(keywords)// 查询内容  
                .setHighlight(true)// 设置高亮显示  
                .setHighlightSimplePre(preTag)// 渲染头标签  
                .setHighlightSimplePost(postTag)// 尾标签  
                .setStart((pageNum - 1) * pageSize)// 分页  
                .setRows(pageSize);//  
        if (distinguish)  
        {  
            query.addFilterQuery("lang:" + lang);// 中英文区别  
        }  
        // 设置高亮区域  
        for (String hl : hlFields)  
        {  
            query.addHighlightField(hl);  
        }  
        QueryResponse response = null;  
  
        try  
        {  
            response = server.query(query);  
        }  
        catch (SolrServerException e)  
        {  
            e.printStackTrace();  
            return null;  
        }  
  
        SolrDocumentList results = response.getResults();  
        // 总记录数  
        int total = new Long(results.getNumFound()).intValue();  
        // 查询结果集  
        ArrayList<T> list = new ArrayList<T>();  
  
        try  
        {  
            Object object = null;  
            Method method = null;  
            Class<?> fieldType = null;  
            Map<String, Map<String, List<String>>> map = response.getHighlighting();  
            for (SolrDocument solrDocument : results)  
            {  
                object = clzz.newInstance();  
                // 得到所有属性名  
                Collection<String> fieldNames = solrDocument.getFieldNames();  
                for (String fieldName : fieldNames)  
                {  
                    Field[] fields = clzz.getDeclaredFields();  
                    for (Field f : fields)  
                    {  
                        // 如果实体属性名和查询返回集中的字段名一致，填充对应的set方法  
                        if (f.getName().equals(fieldName))  
                        {  
                            f = clzz.getDeclaredField(fieldName);  
                            fieldType = f.getType();  
                            // 构造set方法名 setId  
                            String dynamicSetMethod = dynamicMethodName(f.getName(), "set");  
                            // 获取方法  
                            method = clzz.getMethod(dynamicSetMethod, fieldType);  
                            // 获取fieldType类型  
                            // fieldType = getFileType(fieldType);  
                            // 获取到的属性  
                            method.invoke(object, fieldType.cast(solrDocument.getFieldValue(fieldName)));  
                            for (String hl : hlFields)  
                            {  
                                if (hl.equals(fieldName))  
                                {  
                                    String idv = solrDocument.getFieldValue(idName).toString();  
                                    List<String> hfList = map.get(idv).get(fieldName);  
                                    if (null != hfList && hfList.size() > 0)  
                                    {  
                                        // 高亮添加  
                                        method.invoke(object, fieldType.cast(hfList.get(0)));  
                                    }  
                                    else  
                                    {  
                                        method.invoke(object, fieldType.cast(solrDocument.getFieldValue(fieldName)));  
                                    }  
                                }  
                            }  
                        }  
                    }  
                }  
                list.add(clzz.cast(object));  
            }  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            return null;  
        }  
        
        Page<T> page = new Page<T>(pageNum, pageSize);
        page.addAll(list);
        page.setTotal(total);
        return new PageInfo<T>(page);  
    }  
  
    /** 
     * @Description: 动态生成方法名 
     * @author kang 
     * @创建时间 2015下午9:16:59 
     * @param @param name 
     * @param @param string 
     * @param @return 
     * @return String 
     * @throws 
     */  
    private String dynamicMethodName(String name, String string)  
    {  
        if (Character.isUpperCase(name.charAt(0))) return string + name;  
        else return (new StringBuilder()).append(string + Character.toUpperCase(name.charAt(0))).append(name.substring(1)).toString();  
    }  
  
    /** 
     * @Description:因为反射的属性可能是一个集合,所以在利用反射转换之前,需要进行更精确地判断,这实例中实体对象中的属性为简单类型,所以这个方法可以处理 
     * @author kang 
     * @创建时间 2015下午6:59:17 
     * @param @param fieldType 
     * @param @return 
     * @return Class<?> 
     * @throws 
     */  
    public Class<?> getFileType(Class<?> fieldType)  
    {  
        // 如果是 int, float等基本类型，则需要转型  
        if (fieldType.equals(Integer.TYPE))  
        {  
            return Integer.class;  
        }  
        else if (fieldType.equals(Float.TYPE))  
        {  
            return Float.class;  
        }  
        else if (fieldType.equals(Double.TYPE))  
        {  
            return Double.class;  
        }  
        else if (fieldType.equals(Boolean.TYPE))  
        {  
            return Boolean.class;  
        }  
        else if (fieldType.equals(Short.TYPE))  
        {  
            return Short.class;  
        }  
        else if (fieldType.equals(Long.TYPE))  
        {  
            return Long.class;  
        }  
        else if (fieldType.equals(String.class))  
        {  
            return String.class;  
        }  
        else if (fieldType.equals(Collection.class)) { return Collection.class; }  
        return null;  
    }  
  
}  
(function($){
    $.fn.validationEngineLanguage = function(){
    };
    $.validationEngineLanguage = {
        newLang: function(){
            $.validationEngineLanguage.allRules = {
                "required": {    			// Add your regex rules here, you can take telephone as an example
						"regex":"none",
						"alertText":"* 此栏为必填项。",
						"alertTextCheckboxMultiple":"* 请选择一个选项。",
						"alertTextCheckboxe":"* 此选择框为必选。"
						},
				"space" : {
					"regex" : "none",
					"alertText" : "* 此栏不能输入空格。"
				},
                "minSize": {
                    "regex": "none",
                    "alertText": "* 最少字符数要求",
                    "alertText2": ""
                },
                "maxSize": {
                    "regex": "none",
                    "alertText": "* 最大允许输入字符数为 ",
                    "alertText2": ""
                },
				"groupRequired": {
                    "regex": "none",
                    "alertText": "* 你必须填写下面其中一个字段。"
                },
                "min": {
                    "regex": "none",
                    "alertText": "* 最小值是 "
                },
                "max": {
                    "regex": "none",
                    "alertText": "* 最大值是 "
                },
                "past": {
                    "regex": "none",
                    "alertText": "* 过期的日期。 "
                },
                "future": {
                    "regex": "none",
                    "alertText": "* 未到的日期。"
                },
                "maxCheckbox": {
                    "regex": "none",
                    "alertText": "* 最大选择 ",
                    "alertText2": " 合法的选项"
                },
                "minCheckbox": {
                    "regex": "none",
                    "alertText": "* 请选择 ",
                    "alertText2": " 选项"
                },
                "equals": {
                    "regex": "none",
                    "alertText": "* 两次输入不一致,请重新输入"
                },
                "phone": {
                    // credit: jquery.h5validate.js / orefalo
                	"regex": "^((0[0-9]{2,3}\-)?)([1-9][0-9]{6,7})((\-[0-9]{1,4})?)$",
                    "alertText": "* 不合法的电话号码。"
                },
                "mobilephone":{   
                    "regex":/^0?[1][358][0-9]{9}$/,   
                    "alertText":"* 请输入有效的手机号码。"
                },
                "checkphone": {
                    // credit: jquery.h5validate.js / orefalo
                    "regex": /(^(0[0-9]{2,3}\-)?([1-9][0-9]{6,7})+(\-[0-9]{1,4})?$)|(^((\(\d{3}\))|(\d{3}\-))?(1[358]\d{9})$)/,
                    "alertText": "* 不合法的电话号码或手机号码。"
                },
                "email": {
                    // Shamelessly lifted from Scott Gonzalez via the Bassistance Validation plugin http://projects.scottsplayground.com/email_address_validation/
                    "regex": /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i,
                    "alertText": "* 不合法的邮箱地址。"
                },
                "zipcode":{   
                    "regex": /^[1-9]\d{5}$/,   
                    "alertText":"* 请输入有效的邮政编码。"
                },
                "integer": {
                    "regex": /^[\-\+]?\d+$/,
                    "alertText": "* 不合法的整数。"
                },
                "lotteryserialnum": {
                    "regex": /^[\-\+]?\d+$/,
                    "alertText": "* 请输入正确的彩票票号。"
                },
                "number": {
                    // Number, including positive, negative, and floating decimal. credit: orefalo
                    "regex": /^[\-\+]?(([0-9]+)([\.,]([0-9]+))?|([\.,]([0-9]+))?)$/,
                    "alertText": "* 不合法的小数。"
                },
                "money": {
                    // Number, including positive, and floating decimal. credit: orefalo
                    "regex": /^([1-9]\d*(\.\d{1,2})?$)|^([0]{1}(\.\d{1,2})?$)/,
                    "alertText": "* 不合法的金额。"
                },
                "money1": {
                    // Number, including positive, and floating decimal. credit: orefalo
                    "regex": /^\d{1,3}(),\d\d\d)*\.\d\d$|^\d+\.\d\d$|^\d+$/,
                    "alertText": "* 不合法的金额。"
                },
                "date": {
                    "regex": /^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$/,
                    "alertText": "* 不合法的日期,必须类似 YYYY-MM-DD 的格式。"
                },
                "ipv4": {
                    "regex": /^((([01]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))[.]){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))$/,
                    "alertText": "* 不合法的IP地址。"
                },
                "ipv4Group": {
                    "regex": /^((((([01]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))[.]){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5])))；)*(((([01]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))[.]){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5])))$/,
                    "alertText": "* 不合法的IP地址组，每个IP请以全角分号分隔。"
                },
                "url": {
                    "regex": /^(?:(?:ht|f)tp(?:s?)\:\/\/|~\/|\/)?(?:\w+:\w+@)?((?:(?:[-\w\d{1-3}]+\.)+(?:com|org|net|gov|mil|biz|info|mobi|name|aero|jobs|edu|co\.uk|ac\.uk|it|fr|tv|museum|asia|local|travel|[a-z]{2}))|((\b25[0-5]\b|\b[2][0-4][0-9]\b|\b[0-1]?[0-9]?[0-9]\b)(\.(\b25[0-5]\b|\b[2][0-4][0-9]\b|\b[0-1]?[0-9]?[0-9]\b)){3}))(?::[\d]{1,5})?(?:(?:(?:\/(?:[-\w~!$+|.,=]|%[a-f\d]{2})+)+|\/)+|\?|#)?(?:(?:\?(?:[-\w~!$+|.,*:]|%[a-f\d{2}])+=?(?:[-\w~!$+|.,*:=]|%[a-f\d]{2})*)(?:&(?:[-\w~!$+|.,*:]|%[a-f\d{2}])+=?(?:[-\w~!$+|.,*:=]|%[a-f\d]{2})*)*)*(?:#(?:[-\w~!$ |\/.,*:;=]|%[a-f\d]{2})*)?$/i,
                    "alertText": "* 不合法的网址。"
                },
                "onlyNumberSp": {
                    "regex": /^[0-9\ ]+$/,
                    "alertText": "* 只能输入数字。"
                },
                "onlyLetterSp": {
                    "regex": /^[a-zA-Z\ \']+$/,
                    "alertText": "* 只能输入英文字母。"
                },
                "noSpecialCaracters":{
                    "regex":/^[0-9a-zA-Z]+$/,
                    "alertText":"* 只能输入英文字母和数字。"
                },
                "naturalNumber": {
                    "regex": /^[1-9]\d*$/,
                    "alertText": "* 限额不能以0为开头。"
                },
                "notZeroStart": {
                    "regex": /^[1-9]\d*$/,
                    "alertText": "* 银行卡号不能以0为开头。"
                },
                "chinese":{  
                    "regex":"^[\u4e00-\u9fa5]+$",  
                    "alertText":"* 请输入中文。"
                },
                "onlyLetterNumber": {
                    "regex": /^[0-9a-zA-Z]+$/,
                    "alertText": "* 不允许输入特殊字符串。"
                },
                "onlyLetterNumberorcn": {
                    "regex": /^[0-9a-zA-Z\u4e00-\u9fa5]+$/,
                    "alertText": "* 不允许输入特殊字符串。"
                },
                "onlychineseOrPunctuate":{
                	"regex": /^([，]|[。]|[！]|[\u4e00-\u9fa5])+$/,
                    "alertText": "* 只能输入中文，逗号，句号和叹号。"
                },
                "chineseAndLetterAndNumber":{
                	"regex":/^[\u4e00-\u9fa5a-zA-Z0-9]+$/,
                    "alertText":"* 只能输入中文，英文字母和数字。"
                },
                "onlyLetterNumberunderline":{
                	"regex": /^[a-zA-Z]{1}([a-zA-Z0-9]|[_])+$/,
                    "alertText": "* 字母开头6-20个字符，可以使用字母、数字、下划线。"
                },
                "onlyLetterNumberunderlineorchinese":{
                	"regex": /^([a-zA-Z0-9]|[_]|[\u4e00-\u9fa5])+$/,
                    "alertText": "* 6-20个字符，可以使用字母、数字、下划线。"
                },
                "checkpwd": {
                	"regex":/^((?=.*?\d)(?=.*?[A-Za-z])|(?=.*?\d)(?=.*?[!@#$%^&])|(?=.*?[A-Za-z])(?=.*?[!@#$%^&]))[\dA-Za-z!@#$%^&]+$/,
                    "alertText": "* 8-20个字符，请使用字母加数字或符号的组合密码，不能单独使用字母、数字或符号。"
                },
                "checkLcpNum":{
                	"regex": /^(?:[0-2][0-9]?|31){2}[0-9\ ]+$/,
                    "alertText": "* 只能输入4个数字，并且前2位不能大于31。"
                },
                "checkLuserIdentity":{
                	"regex":/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
                	"alertText":"* 请输入正确的身份证号码。"
                },
                "checkBankAccount":{
                	"regex":/(^\d{19}$)|(^\d{16}$)/,
                	"alertText":"* 银行卡卡号只能为19位或者16位数字。"
                },
                // --- CUSTOM RULES -- Those are specific to the demos, they can be removed or changed to your likings
                "ajaxUserCall": {
                    "url": "/lsmp-manage-web/lcpSaveController/checkcnname.htm",
                    // you may want to pass extra data on the ajax call
                    "alertTextOk": "*恭喜你， 此用户名可以使用",
                    "alertText": "* 该用户名已经存在",
                    "alertTextLoad": "* 正在验证中，请稍等..."
                },
                "ajaxAccountNameCall": {
                    "url": "/lsmp-manage-web/accountController/checkaccountname.htm",
                    // you may want to pass extra data on the ajax call
                    "alertTextOk": "*恭喜你， 此操作员名称可以使用",
                    "alertText": "* 该操作员名称已经存在",
                    "alertTextLoad": "* 正在验证中，请稍等..."
                },
                "ajaxAccountTelephoneCall": {
                    "url": "/lsmp-manage-web/accountController/checkaccounttelephone.htm",
                    // you may want to pass extra data on the ajax call
                    "alertTextOk": "*恭喜你， 此手机号码可以使用",
                    "alertText": "* 该手机号码已被使用",
                    "alertTextLoad": "* 正在验证中，请稍等..."
                },
                "ajaxAccountEmailCall": {
                    "url": "/lsmp-manage-web/accountController/checkaccountemail.htm",
                    // you may want to pass extra data on the ajax call
                    "alertTextOk": "*恭喜你， 此邮箱可以使用",
                    "alertText": "* 该邮箱已被使用",
                    "alertTextLoad": "* 正在验证中，请稍等..."
                },
                "ajaxRoleNameCall": {
                    "url": "/lsmp-manage-web/roleController/checkrolename.htm",
                    // you may want to pass extra data on the ajax call
                    "alertTextOk": "*恭喜你， 此角色名称可以使用",
                    "alertText": "* 该角色名称已经存在",
                    "alertTextLoad": "* 正在验证中，请稍等..."
                },
				"ajaxUserCallPhp": {
                    "url": "phpajax/ajaxValidateFieldUser.php",
                    // you may want to pass extra data on the ajax call
                    "extraData": "name=eric",
                    // if you provide an "alertTextOk", it will show as a green prompt when the field validates
                    "alertTextOk": "* 恭喜你，此用户名可以使用",
                    "alertText": "* 该用户名已经存在",
                    "alertTextLoad": "* 正在验证中，请稍等..."
                },
                "ajaxNameCall": {
                    // remote json service location
                    "url": "/lsmp-manage-web/lcpSaveController/checkExt3.htm",
                    // error
                    "alertText": "* 该登记号已经存在",
                    // if you provide an "alertTextOk", it will show as a green prompt when the field validates
                    "alertTextOk": "* 恭喜你，这个登记号可以使用",
                    // speaks by itself
                    "alertTextLoad": "* 正在验证中，请稍等..."
                },
				 "ajaxNameCallPhp": {
	                    // remote json service location
	                    "url": "phpajax/ajaxValidateFieldName.php",
	                    // error
	                    "alertText": "* 该名称已经存在",
	                    // speaks by itself
	                    "alertTextLoad": "* 正在验证中，请稍等..."
	                },
                 "ajaxLcpNumCall": {
                    "url": "/lsmp-manage-web/lcpSaveController/checkLcpNum.htm",
                    // if you provide an "alertTextOk", it will show as a green prompt when the field validates
                    "extraData": "name=eric",
                    "extraDataDynamic": ['#lcpLevle', '#lcpLevle'],
                    "alertText": "* 该渠道编号已经存在",
					"alertTextOk": "* 恭喜你，此渠道编号可以使用",
                    "alertTextLoad": "* 正在验证中，请稍等..."
                },
                "ajaxAdminNameCall": {
                	"url": "/lsmp-manage-web/lcpQueryController/checkAdminName.htm",
                	// if you provide an "alertTextOk", it will show as a green prompt when the field validates
                	//"extraData": "name=eric",
                	"extraDataDynamic": "#lcpNum,#lcpName",
                	"alertText": "* 该用户名已经存在",
                	"alertTextOk": "* 恭喜你，此用户名可以使用",
                	"alertTextLoad": "* 正在验证中，请稍等..."
                },
                "validate2fields": {
                    "alertText": "* 请输入HELLO"
                },
	            //tls warning:homegrown not fielded
                "dateFormat":{
                    "regex": /^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$|^(?:(?:(?:0?[13578]|1[02])(\/|-)31)|(?:(?:0?[1,3-9]|1[0-2])(\/|-)(?:29|30)))(\/|-)(?:[1-9]\d\d\d|\d[1-9]\d\d|\d\d[1-9]\d|\d\d\d[1-9])$|^(?:(?:0?[1-9]|1[0-2])(\/|-)(?:0?[1-9]|1\d|2[0-8]))(\/|-)(?:[1-9]\d\d\d|\d[1-9]\d\d|\d\d[1-9]\d|\d\d\d[1-9])$|^(0?2(\/|-)29)(\/|-)(?:(?:0[48]00|[13579][26]00|[2468][048]00)|(?:\d\d)?(?:0[48]|[2468][048]|[13579][26]))$/,
                    "alertText": "* 非法的日期格式"
                },
                //tls warning:homegrown not fielded
				"dateTimeFormat": {
	                "regex": /^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])\s+(1[012]|0?[1-9]){1}:(0?[1-5]|[0-6][0-9]){1}:(0?[0-6]|[0-6][0-9]){1}\s+(am|pm|AM|PM){1}$|^(?:(?:(?:0?[13578]|1[02])(\/|-)31)|(?:(?:0?[1,3-9]|1[0-2])(\/|-)(?:29|30)))(\/|-)(?:[1-9]\d\d\d|\d[1-9]\d\d|\d\d[1-9]\d|\d\d\d[1-9])$|^((1[012]|0?[1-9]){1}\/(0?[1-9]|[12][0-9]|3[01]){1}\/\d{2,4}\s+(1[012]|0?[1-9]){1}:(0?[1-5]|[0-6][0-9]){1}:(0?[0-6]|[0-6][0-9]){1}\s+(am|pm|AM|PM){1})$/,
	                "alertText": "* 非法的日期格式\n必须是类似如下的日期格式: mm/dd/yyyy hh:mm:ss AM|PM or yyyy-mm-dd hh:mm:ss AM|PM"
	            },
	            "confirm": { 
                	"regex":"none", 
                	"alertText":"* 两次输入不一致,请重新输入."
                }
            };

        }
    };
    $.validationEngineLanguage.newLang();
})(jQuery);
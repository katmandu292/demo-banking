<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>E-Banking Account Topup Page</title>
	<!-- reference our style sheet -->
	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" /></head>
<script>
var EditorConfig = {
        shell: true,
        paths: {
          favorite:         "/_make_favourite/",
          media:            "/mooshellmedia/",
          addResource:      "/_add_external_resource/",
          render:           "//fiddle.jshell.net/2ozoovne/1/show/?editor_console=",
          saveSettings:     "/_editor_options/",
          fork:             "/_fork/",
          save:             "/_save/",
          update:           "/_update/2ozoovne/",
          loadDependencies: "/_get_dependencies/{lib_id}/",
          showProfile:      "/_show_profile/"
        },
        value: {
          html: "<H1>Bind Context 1<\/H1>\n<input id=\'a\' data-bind=\'data.test\' placeholder=\'Button Text\' />\n<input id=\'b\' data-bind=\'data.test\' placeholder=\'Button Text\' />\n<input type=button id=\'c\' data-bind=\'data.test\' />\n<H1>Bind Context 2<\/H1>\n<input id=\'d\' data-bind=\'data.otherTest\' placeholder=\'input bind\' />\n<input id=\'e\' data-bind=\'data.otherTest\' placeholder=\'input bind\' />\n<input id=\'f\' data-bind=\'data.test\' placeholder=\'button 2 text - same var name, other context\' />\n<input type=button id=\'g\' data-bind=\'data.test\' value=\'click here!\' />\n<H1>No bind data<\/H1>\n<input id=\'h\' placeholder=\'not bound\' />\n<input id=\'i\' placeholder=\'not bound\'/>\n<input type=button id=\'j\' />\n",
          js:   "(function(){\n	if ( ! ( \'SmartBind\' in window ) ) { // never run more than once\n		// This hack sets a \"proxy\" property for HTMLInputElement.value set property\n		var nativeHTMLInputElementValue = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, \'value\');\n		var newDescriptor = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, \'value\');\n		newDescriptor.set=function( value ){\n			if ( \'settingDomBind\' in this )\n				return;\n			var hasDataBind=this.hasAttribute(\'data-bind\');\n			if ( hasDataBind ) {\n				this.settingDomBind=true;\n				var dataBind=this.getAttribute(\'data-bind\');\n				if ( ! this.hasAttribute(\'data-bind-context-id\') ) {\n					console.error(\"Impossible to recover data-bind-context-id attribute\", this, dataBind );\n				} else {\n					var bindContextId=this.getAttribute(\'data-bind-context-id\');\n					if ( bindContextId in SmartBind.contexts ) {\n						var bindContext=SmartBind.contexts[bindContextId];\n						var dataTarget=SmartBind.getDataTarget(bindContext, dataBind);\n						SmartBind.setDataValue( dataTarget, value);\n					} else {\n						console.error( \"Invalid data-bind-context-id attribute\", this, dataBind, bindContextId );\n					}\n				}\n				delete this.settingDomBind;\n			}\n			nativeHTMLInputElementValue.set.bind(this)( value );\n		}\n		Object.defineProperty(HTMLInputElement.prototype, \'value\', newDescriptor);\n\n	var uid= function(){\n           return \'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx\'.replace(/[xy]/g, function(c) {\n               var r = Math.random()*16|0, v = c == \'x\' ? r : (r&0x3|0x8);\n               return v.toString(16);\n          });\n   }\n\n		// SmartBind Functions\n		window.SmartBind={};\n		SmartBind.BindContext=function(){\n			var _data={};\n			var ctx = {\n				\"id\" : uid()	/* Data Bind Context Id */\n				, \"_data\": _data		/* Real data object */\n				, \"mapDom\": {}			/* DOM Mapped objects */\n				, \"mapDataTarget\": {}		/* Data Mapped objects */\n			}\n			SmartBind.contexts[ctx.id]=ctx;\n			ctx.data=new Proxy( _data, SmartBind.getProxyHandler(ctx, \"data\"))	/* Proxy object to _data */\n			return ctx;\n		}\n\n		SmartBind.getDataTarget=function(bindContext, bindPath){\n			var bindedObject=\n				{ bindContext: bindContext\n				, bindPath: bindPath \n				};\n			var dataObj=bindContext;\n			var dataObjLevels=bindPath.split(\'.\');\n			for( var i=0; i<dataObjLevels.length; i++ ) {\n				if ( i == dataObjLevels.length-1 ) { // last level, set value\n					bindedObject={ target: dataObj\n					, item: dataObjLevels[i]\n					}\n				} else {	// digg in\n					if ( ! ( dataObjLevels[i] in dataObj ) ) {\n						console.warn(\"Impossible to get data target object to map bind.\", bindPath, bindContext);\n						break;\n					}\n					dataObj=dataObj[dataObjLevels[i]];\n				}\n			}\n			return bindedObject ;\n		}\n\n		SmartBind.contexts={};\n		SmartBind.add=function(bindContext, domObj){\n			if ( typeof domObj == \"undefined\" ){\n				console.error(\"No DOM Object argument given \", bindContext);\n				return;\n			}\n			if ( ! domObj.hasAttribute(\'data-bind\') ) {\n				console.warn(\"Object has no data-bind attribute\", domObj);\n				return;\n			}\n			domObj.setAttribute(\"data-bind-context-id\", bindContext.id);\n			var bindPath=domObj.getAttribute(\'data-bind\');\n			if ( bindPath in bindContext.mapDom ) {\n				bindContext.mapDom[bindPath][bindContext.mapDom[bindPath].length]=domObj;\n			} else {\n				bindContext.mapDom[bindPath]=[domObj];\n			}\n			var bindTarget=SmartBind.getDataTarget(bindContext, bindPath);\n			bindContext.mapDataTarget[bindPath]=bindTarget;\n			domObj.addEventListener(\'input\', function(){ SmartBind.setDataValue(bindTarget,this.value); } );\n			domObj.addEventListener(\'change\', function(){ SmartBind.setDataValue(bindTarget, this.value); } );\n		}\n\n		SmartBind.setDataValue=function(bindTarget,value){\n			if ( ! ( \'target\' in bindTarget ) ) {\n				var lBindTarget=SmartBind.getDataTarget(bindTarget.bindContext, bindTarget.bindPath);\n				if ( \'target\' in lBindTarget ) {\n					bindTarget.target=lBindTarget.target;\n					bindTarget.item=lBindTarget.item;\n				} else {\n					console.warn(\"Still can\'t recover the object to bind\", bindTarget.bindPath );\n				}\n			}\n			if ( ( \'target\' in bindTarget ) ) {\n				bindTarget.target[bindTarget.item]=value;\n			}\n		}\n		SmartBind.getDataValue=function(bindTarget){\n			if ( ! ( \'target\' in bindTarget ) ) {\n				var lBindTarget=SmartBind.getDataTarget(bindTarget.bindContext, bindTarget.bindPath);\n				if ( \'target\' in lBindTarget ) {\n					bindTarget.target=lBindTarget.target;\n					bindTarget.item=lBindTarget.item;\n				} else {\n					console.warn(\"Still can\'t recover the object to bind\", bindTarget.bindPath );\n				}\n			}\n			if ( ( \'target\' in bindTarget ) ) {\n				return bindTarget.target[bindTarget.item];\n			}\n		}\n		SmartBind.getProxyHandler=function(bindContext, bindPath){\n			return	{\n				get: function(target, name){\n					if ( name == \'__isProxy\' )\n						return true;\n					// just get the value\n					// console.debug(\"proxy get\", bindPath, name, target[name]);\n					return target[name];\n				}\n				,\n				set: function(target, name, value){\n					target[name]=value;\n					bindContext.mapDataTarget[bindPath+\".\"+name]=value;\n					SmartBind.processBindToDom(bindContext, bindPath+\".\"+name);\n					// console.debug(\"proxy set\", bindPath, name, target[name], value );\n					// and set all related objects with this target.name\n					if ( value instanceof Object) {\n						if ( !( name in target) || ! ( target[name].__isProxy ) ){\n							target[name]=new Proxy(value, SmartBind.getProxyHandler(bindContext, bindPath+\'.\'+name));\n						}\n						// run all tree to set proxies when necessary\n						var objKeys=Object.keys(value);\n						// console.debug(\"...objkeys\",objKeys);\n						for ( var i=0; i<objKeys.length; i++ ) {\n							bindContext.mapDataTarget[bindPath+\".\"+name+\".\"+objKeys[i]]=target[name][objKeys[i]];\n							if ( typeof value[objKeys[i]] == \'undefined\' || value[objKeys[i]] == null || ! ( value[objKeys[i]] instanceof Object ) || value[objKeys[i]].__isProxy )\n								continue;\n							target[name][objKeys[i]]=new Proxy( value[objKeys[i]], SmartBind.getProxyHandler(bindContext, bindPath+\'.\'+name+\".\"+objKeys[i]));\n						}\n						// TODO it can be faster than run all items\n						var bindKeys=Object.keys(bindContext.mapDom);\n						for ( var i=0; i<bindKeys.length; i++ ) {\n							// console.log(\"test...\", bindKeys[i], \" for \", bindPath+\".\"+name);\n							if ( bindKeys[i].startsWith(bindPath+\".\"+name) ) {\n								// console.log(\"its ok, lets update dom...\", bindKeys[i]);\n								SmartBind.processBindToDom( bindContext, bindKeys[i] );\n							}\n						}\n					}\n					return true;\n				}\n			};\n		}\n		SmartBind.processBindToDom=function(bindContext, bindPath) {\n			var domList=bindContext.mapDom[bindPath];\n			if ( typeof domList != \'undefined\' ) {\n				try {\n					for ( var i=0; i < domList.length ; i++){\n						var dataTarget=SmartBind.getDataTarget(bindContext, bindPath);\n						if ( \'target\' in dataTarget )\n							domList[i].value=dataTarget.target[dataTarget.item];\n						else\n							console.warn(\"Could not get data target\", bindContext, bindPath);\n					}\n				} catch (e){\n					console.warn(\"bind fail\", bindPath, bindContext, e);\n				}\n			}\n		}\n	}\n})();\n\n// SmartBind END ====\n\nvar bindContext=SmartBind.BindContext();\nSmartBind.add(bindContext, document.getElementById(\'a\'));\nSmartBind.add(bindContext, document.getElementById(\'b\'));\nSmartBind.add(bindContext, document.getElementById(\'c\'));\n\nvar bindContext2=SmartBind.BindContext();\nSmartBind.add(bindContext2, document.getElementById(\'d\'));\nSmartBind.add(bindContext2, document.getElementById(\'e\'));\nSmartBind.add(bindContext2, document.getElementById(\'f\'));\nSmartBind.add(bindContext2, document.getElementById(\'g\'));\n\nsetTimeout( function() {\n	document.getElementById(\'b\').value=\'Via Script works too!\'\n}, 2000);\n\ndocument.getElementById(\'g\').addEventListener(\'click\',function(){\nbindContext2.data.test=\'Set by js value\'\n})",
          css:  ""
        },
        fiddle: {
          id:   "273257595",
          slug: "2ozoovne",
          boilerplate: false
        },
        panels: {
          html: "html",
          js:   "javascript",
          css:  "css"
        },
        user: {
          id:       "None",
          username: ""
        },
        // showHelloBar: false,
        showHelloBar: false,
        currentUser:  false,
        isAuthor:     false,
        features: {
          toggleSidebar: false
        }
      }

      // translations
      const I18n = {
        editor: {
          panels: {
            result: "Result",
            drag_to_reorder: "Drag tabs to reorder",
            tidy: "Tidy"
          },
          sidebar: {
            toggle_sidebar: "Toggle sidebar"
          },
          groups: {
            placeholder_value:        "Assign to groups",
            search_placeholder_value: "Search for groups",
            no_choices_text:          "No more groups",
            no_results_text:          "No groups found",
            item_select_text:         "Press to select",
            you_have_no_groups:       "You have no groups"
          }
        }
      }

        const EditorUISettings = {
          // options that user can change
          layout:            1,
          darkTheme:         true,
          tabSize:           2,
          matchBrackets:     true,
          lineNumbers:       true,
          lineWrapping:      true,
          keyMap:            "default",
          autoCloseTags:     true,
          autoCloseBrackets: true,
          indentWithTabs:    false,
          codeLinting:       true,
          autoRun:           EditorConfig.currentUser ? false : false,
          autoRunValid:      EditorConfig.currentUser ? true : false,
          autoSave:          EditorConfig.currentUser ? true : false,
          clearConsole:      false,
          fontSize:          1,
          matchTags:         false,
          foldGutter:        true,
          reduceHelloBar:    false,
          codeHints:         false,
          editorConsole:     true
        }
</script>

</head>
<body>

<table width="750" border="0">
   <tr>
      <td width="14">&nbsp;</td>
	  <td width="180">&nbsp;</td>
	  <td>&nbsp;</td>
	  <td>
		<c:if test="${client.clientUserName == anonymousUser}">
		    Welcome. Please Login
		</c:if>
		<c:if test="${client.clientUserName != anonymousUser}">
      	    Hello,&nbsp;
			<security:authentication property="principal.username" />
<!--  	    Hello,&nbsp;${userName} -->
		</c:if>
	  </td>
	  <td>Date/Time:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<c:if test="${client.clientUserName == anonymousUser}">
			<a href="${pageContext.request.contextPath}/giveMeASession">Login</a>
		</c:if>
		<c:if test="${client.clientUserName != anonymousUser}">
			<form:form action="${pageContext.request.contextPath}/logout"  method="GET">
			<input type="submit" value="Logout" class="add-button" />
			</form:form>
		</c:if>
      </td>
	  <td>&nbsp;</td>
   </tr>

   <tr>
      <td colspan="2"><h1>&nbsp;&nbsp;eBanking</h1></td>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
   </tr>

   <tr>
      <td>&nbsp;</td>
	  <td><h3>Conturi&nbsp;</h3></td>
	  <td><h3>Opera&#355;ii&nbsp;</h3></td>
	  <td><h3>Rapoarte&nbsp;</h3></td>
	  <td><h3>Aprobare&nbsp;</h3></td>
	  <td>&nbsp;</td>
   </tr>

   <tr>
      <td>&nbsp;</td>
	  <td><a href="${pageContext.request.contextPath}/bank/manageAccts">Administrare Cont</a>&nbsp;</td>
	  <td>Transfer&nbsp;</td>
	  <td>Rapoarte&nbsp;</td>
	  <td>Aprobare&nbsp;</td>
	  <td>&nbsp;</td>
   </tr>

   <tr>
      <td>&nbsp;</td>
	  <td><a href="${pageContext.request.contextPath}/bank/showAccounts">Vizualizare Conturi</a>&nbsp;</td>
	  <td>Opera&#355;ii &#238;n curs&nbsp;</td>
	  <td>Extras Cont&nbsp;</td>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
   </tr>

   <tr>
      <td>&nbsp;</td>
	  <td>&nbsp;</td>
	  <td><a href="${pageContext.request.contextPath}/bank/payMoney">Alimentare Conturi</a>&nbsp;</td>
	  <td><a href="${pageContext.request.contextPath}/bank/showTransacts">Lista de Tranzactii</a>&nbsp;</td>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
   </tr>

   <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
   </tr>

<form:form action="${pageContext.request.contextPath}/bank/acceptPayment" modelAttribute="client" method="POST">

   <tr>
      <td>&nbsp;</td>
      <td><label>Contul Vizat</label>&nbsp;</td>
	  <td><label>Sold</label>&nbsp;</td>
	  <td><label>Suma</label>&nbsp;&nbsp;</td>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
   </tr>

   <tr>
      <td>&nbsp;</td>
	  <td><form:select id="acctId" path="accountId" data-bind="data.otherTest">
		  <form:options items="${bankAccountsList}" />
		  </form:select></td>
	  <td><form:input id="acctSum" data-bind="data.otherTest" type="text" path="accountSum" />&nbsp;</td>
	  <td><form:input type="text" path="transaction.movedAmount" /></td>
	  <td><input type="submit" value="Add" class="save" /></td>
	  <td></td>
   </tr>

</form:form>

   <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
   </tr>

   <tr>
      <td>&nbsp;</td>
      <td colspan="5">
        <table>
          <tr>
            <td>Account NR&nbsp;</td>
            <td>Owner&nbsp;</td>
            <td>IBAN&nbsp;</td>
            <td>Sold&nbsp;</td>
            <td>Status&nbsp;</td>
          </tr>
<c:forEach var="crtAccount" items="${client.acctsList}">
          <tr>
            <td>${crtAccount.accountId}&nbsp;</td>
            <td>${client.clientFullName}&nbsp;</td>
            <td>${crtAccount.accountIban}&nbsp;</td>
            <td>${crtAccount.finalSum}&nbsp;</td>
            <td>${crtAccount.statusId}&nbsp;</td>
          </tr>
</c:forEach>
        </table>
      </td>
   </tr>
   <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
   </tr>

   <tr>
      <td colspan="6">${client}&nbsp;</td>
   </tr>


   <tr>
      <td>&nbsp;</td>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
	  <td colspan="3">Autor: C&#259;t&#259;lin (Catman) Dumitra&#351;cu (2021)&nbsp;</td>
   </tr>

</table>
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<jsp:include page="/include/default.jsp"></jsp:include>
	<title>菜单管理</title>
	<script type="text/javascript">
		$(function(){
			$("#ul_menu_tree").tree({
				onDblClick: function(node){
					var map = getNodeMsg(node);
					$("#div_config :input[name]").each(function(i,n){
						var ns = n.name.split(".");
						$(n).val(map[ns.length>1?ns[1]:ns[0]]);
					});
					if(map.logo!=null && map.logo!=""){
						$('#inp_logo_img').attr('src',"${basePath }/"+$('#inp_logo').val());
					}else{
						$('#inp_logo_img').attr('src',"${basePath }/images/index/defaulticon.png");
					}
					/*$("#div_config input").each(function(i,n){n.value = "";});
					var msg = getNodeMsg(node);
					for(var key in msg){
						var inp = document.getElementById("inp_"+key);
						if(inp != null){
							inp.value = msg[key];
						}
					}*/
				}
			});//初始化菜单
			
			initMenuTree();//初始化菜单
			
			$("#fileTree").tree({
				onBeforeExpand:function(node) {
					$("#fileTree").tree("options").url = "${basePath }/view/system/menuTree!fileTree.action?filePath="+node.id;
				},
				onDblClick:function(node) {
					//if(node.state) { return false; }
					$('#inp_url').val('/'+node.id);
					$('#win_menu').dialog('close');
				}
			});
			
		});
		
		var isInitLogs = false;
		function initDivLogos() {
			if(!isInitLogs){
				var url = "${basePath }/view/system/menuTree!fileTree.action?filePath=/images/logos/";
				$.ajax( {
					type: "POST",	url: url,  dataType: 'json',
					success: function(json){
						var html = "";
						for(var i=0;i<json.length;i++){
							html += '<img src="${basePath }/'+json[i].id+'" imgPath="/'+json[i].id+'" style="margin:2px 2px 2px 2px;cursor: pointer;"/>';
						}
						$('#div_logo').append(html);
						$('#div_logo img').click(function(){
							$('#inp_logo').val($(this).attr('imgPath'));
							$('#inp_logo_img').attr('src',"${basePath }/"+$('#inp_logo').val());
							$('#win_menu_logo').dialog('close');
						});
					},	error: function(e) {alert("获取logo异常");}
				});
			}
			isInitLogs = true;
		}
		
		function getNodeMsg(node){
			var obj = new Object();
			obj.menuId = node.id;
			obj.menuName = node.text;
			if(node.attributes!=null){
				obj.url = node.attributes.url;
				obj.order = node.attributes.order;
				obj.menuParentIds = node.attributes.menuParentIds;
				obj.openMethod = node.attributes.openMethod;
				obj.openMethod = obj.openMethod==null?"tab":obj.openMethod;
				obj.logo = node.attributes.logo;
			}
			var pnode = $('#ul_menu_tree').tree('getParent',node.target); 
			if(pnode!=null){
				obj.menuParentId = pnode.id;
				obj.menuParentName = pnode.text;
			}
			return obj;
		}
		
		//初始化功能菜单
		function initMenuTree() {
			$.ajax( {
				type : "POST",
				url : "menuTree.action",
				dataType : "json",
				success : function(json) {
					var root = json.trees[0];
					root.state = "open";
					//root.children[root.children.length - 1].state = "open";
					$("#ul_menu_tree").tree("loadData", json.trees);
				}
			});
		}
		
		function addTree(){
			var node = $("#ul_menu_tree").tree("getSelected");
			if(node==null){
				alert("请选择一个菜单");
				return false;
			}
			var msg = getNodeMsg(node);
			if(msg.menuParentIds!=null && msg.menuParentIds.length>0) {
				var pids = msg.menuParentIds.split(",");
				if(pids.length>=5) {
					alert("当前节点已经为菜单节点，不能新增子菜单");
					return false;
				} 
			}
			if(msg.url==null || msg.url==""){
				$("#div_config input").each(function(i,n){n.value = "";});
				document.getElementById("inp_menuParentId").value = msg.menuId;
				document.getElementById("inp_menuParentName").value = msg.menuName;
				document.getElementById("inp_menuParentIds").value = msg.menuParentIds;
			}else{
				alert("当前所选的节点为功能菜单，不能作为父菜单");
				return false;
			}
		}
		
		function saveTree(json){
			var pid = document.getElementById("inp_menuParentIds").value;
			var url = document.getElementById("inp_url").value;
			if(pid != null && pid.length > 0) {
				var pids = pid.split(",");
				if(pids.length >= 3) {
					if(url.length == 0) {
						//alert("请填写链接地址！");
						//return;
					}
					//$('#inp_url').validatebox({   
						//required:true  
					//}); 
				} else {
					if(url.length > 0) {
						alert("此节点不为根节点，不能输入链接地址！");
						return;
					}
				}
			}
			
			if(json == null) {
				$("#div_config").attr("action","treeSave.action");
				formSubmit('div_config',saveTree);
			} else {
				document.getElementById("inp_menuId").value = json.tree.menuId;
				document.getElementById("inp_menuParentIds").value = json.tree.menuParentIds;
				initMenuTree();//初始化菜单
				alert("保存成功");
			}
		}
		
		function deleteTree(json){
			if(json==null){
				window.confirm("提示","确认删除?", function(r){
					if(r){
						$("#div_config").attr("action","treeDelete.action");
						formSubmit('div_config',deleteTree);
					}
				});
			}else{
				$("#div_config input").each(function(i,n){n.value = "";});
				initMenuTree();//初始化菜单
				alert("删除成功");
			}
		}
		
		function formToMenu(id,name){
			var url = "/view/form/form!edit.action?formConfig.formId="+id;
			$("#inp_menuName").val(name);
			$("#inp_url").val(url);
			$('#win_forms').dialog('close');
		}
		function showForms(){
			$("#tabForms").datagrid({url:"${basePath}/view/form/formConfig!list.action?form.formatterFlag=1"});
			$('#win_forms').dialog('open');
		}
		var formatterFormsAction = function(val, data, index){
	    	return "<a href='javascript:void(0);' onclick='formToMenu(\""+data.formId+"\",\""+data.formName+"\");'>选择</a>&nbsp;";
	    }
	    
	</script>
  </head>
  
   <body class="easyui-layout">
  	<div region="west" class="easyui-panel bgColor" split="true" title="功能菜单列表" style="width:200px;overflow: auto;">
  		<ul id="ul_menu_tree" ></ul>
  	</div>
  	<div id="div_config" region="center" title="功能菜单信息" class="easyui-panel bgColor" style="overflow: auto;">
		<table class="tablestyle01" style="margin-top:5px;" width="100%">
			<tr>
				<td width="110">菜单ID：</td>
				<td><input name="tree.menuId" id="inp_menuId" readonly="readonly" style="width: 460px;" /></td>
			</tr>
			<tr>
				<td>上级菜单ID：</td>
				<td><input name="tree.menuParentId" id="inp_menuParentId" readonly="readonly" style="width: 460px;" /></td>
			</tr>
			<tr>
				<td>上级菜单名称：</td>
				<td><input id="inp_menuParentName" name="menuParentName" readonly="readonly" style="width: 460px;" /></td>
			</tr>
			<tr>
				<td>上级菜单ID字符串：</td>
				<td><input id="inp_menuParentIds" name="tree.menuParentIds" style="width: 460px;" /></td>
			</tr>
			<tr>
				<td>排序序号：</td>
				<td><input name="tree.order" id="inp_order" class="easyui-validatebox" required="true" validType="number" maxlength="3" style="width: 50px;" /></td>
			</tr>
			<tr>
				<td>菜单名称：</td>
				<td><input name="tree.menuName" id="inp_menuName" class="easyui-validatebox" required="true" maxlength="100" style="width: 320px;" /></td>
			</tr>
			<tr>
				<td>链接地址：</td>
				<td>
					<input name="tree.url" id="inp_url" class="easyui-validatebox"  maxlength="2000" style="width: 360px;" />
					<!-- <a href="javascript:void(0);" onclick="showForms();">表单</a>&nbsp; -->
					<a href="javascript:void(0);" onclick="$('#win_menu').dialog('open');">选择路径</a>
				</td>
			</tr>
			<tr>
				<td>打开方式：</td>
				<td>
					<select name="tree.openMethod">
						<option value="tab">tab页</option>
						<!-- <option value="tabs">多个tab页</option> -->
						<option value="open">open打开</option>
						<!-- <option value="dialog">dialog打开</option> -->
					</select>
				</td>
			</tr>
			<!-- <tr>
				<td>菜单图标：</td>
				<td>
					<input name="tree.logo" id="inp_logo" type="hidden" maxlength="2000" style="width: 360px;" />
					<img id="inp_logo_img" src="${basePath }/images/index/suoyouhetong.png"/>
					<a href="javascript:void(0);" onclick="initDivLogos();$('#win_menu_logo').dialog('open');">选择</a>
				</td>
			</tr> -->
			<tr>
				<td>注释：</td>
				<td style="color: red;">菜单最多为三级，第三级菜单为功能菜单，新增菜单时：排序序号、菜单名称为必填，功能菜单链接地址为必填！</td>
			</tr>
			<tr>
				<td>操作说明：</td>
				<td style="color: red;">
					保存：则双击某菜单，输入正确的信息后点击保存按钮！<br/>
					新增：要为菜单添加子菜单，则单击选择该菜单，点击新增按钮，按要求在右侧输入菜单信息后，点击保存！<br/>
					删除：双击选择要删除的菜单后，点击删除按钮！<br/>
				</td>
			</tr>
		</table>
		
		<div id="win_menu" title="菜单选择" class="easyui-dialog" closed="true" data-options="width:600,height:350" buttons="#win_menu_buttons">
			<ul class="easyui-tree" url="${basePath }/view/system/menuTree!fileTree.action" id="fileTree"></ul>
			<div id="win_menu_buttons">
 				<a href="#" class="easyui-linkbutton" onclick="$('#win_menu').dialog('close');">关闭</a>
			</div>
		</div>
		<div id="win_menu_logo" title="菜单图标" class="easyui-dialog" closed="true" data-options="width:350,height:280" buttons="#win_menu_logo_buttons">
			<div id="div_logo"></div>
			<div id="win_menu_logo_buttons">
 				<a href="#" class="easyui-linkbutton" onclick="$('#win_menu_logo').dialog('close');">关闭</a>
			</div>
		</div>
		
		<div id="win_forms" title="表单列表" class="easyui-dialog" closed="true" style="width:680; height:360;" modal="true">
		  	<table id="tabForms" class="easyui-datagrid" border="true" style="width:660; height:320;" rownumbers="true"
		  	 data-options="pagination:true">
		    	<thead>
			    	<tr>
			    		<th field="formName" width="120" >表单名称</th>
			    		<th field="formVersion" width="80" >表单版本</th>
			    		<th field="flag" width="80" formatter="formatterFlag">是否有效</th>
			    		<th field="userId" width="80" >创建人</th>
			    		<th field="createTime" width="80" formatter="formatterDatetime">创建时间</th>
			    		<th field="edit" width="140" formatter="formatterFormsAction">操作</th>
			    	</tr>
		    	</thead>
		    </table>
	    </div>
  	</div>
    <div region="south" class="easyui-panel bgColor" align="center" style="height:32px; padding-top: 3px; ">
    	<a href="javascript:void(0);" class="easyui-linkbutton" onclick="saveTree();">保存</a>
 		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="addTree();">新增</a>
 		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="deleteTree();">删除</a>
 		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="winReload();">刷新</a>
    </div>
  </body>
</html>
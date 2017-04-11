<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="dialog open">
	<div class="dialog-body">
		<input type="hidden" name="id" value="${folder.id }" id="dataId" />
		<div class="form-group">
			<div class="label">
				<label>项目</label>
			</div>
			<div class="field">
				<select class="input input-auto" name="projectId">
					<c:forEach items="${projects }" var="project">
						<option value="${project.id }"
							${project.id==folder.projectId?'selected':'' }>${project.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>name</label>
			</div>
			<div class="field">
				<input type="text" class="input input-auto" name="name" value="${folder.name }" data-validate="required:请填写信息" size="20" />
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>remark</label>
			</div>
			<div class="field">
				<textarea class="input" id="remarkEditor" name="remark" style="width:600px;height:240px;">${folder.remark }</textarea>
			</div>
		</div>
		<div class="dialog-foot">
			<button class="button bg-green" type="submit">保存</button>
		</div>
	</div>
</div>
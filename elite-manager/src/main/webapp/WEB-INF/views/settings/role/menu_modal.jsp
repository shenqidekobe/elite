<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<div id="dlg_right" class="modal fade" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="smaller lighter blue no-margin">设置权限</h3>
            </div>

            <div class="modal-body" style="max-height: 600px">
                <div style="width: 100%;height: 600px;">
                    <div name="jstree">
                    </div>
                    <div name="jstree_over" class="maskLayer" style="display: none">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button name="btn_sub" class="btn btn-sm btn-info pull-right">
                    <i class="ace-icon fa fa-check"></i>
                    保存
                </button>
                <button class="btn btn-sm btn-danger pull-right" data-dismiss="modal">
                    <i class="ace-icon fa fa-times"></i>
                    关闭
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
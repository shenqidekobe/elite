
		<div class="table_withdrawal">
			<div class="table_head clearfloat">
				<div class="part" style="width:18%;border-right:1px solid #fff;">提现时间</div>
				<div class="part" style="width:18%;border-right:1px solid #fff;">提现金额<span>(元)</span></div>
				<div class="part" style="width:18%;border-right:1px solid #fff;">到账渠道</div>
				<div class="part" style="width:28%;border-right:1px solid #fff;">账号</div>
				<div class="part" style="width:18%;">状态</div>
			</div>
			  <#list list as it>
			<div class="table_body clearfloat">
				<div class="group">
					<div class="part" style="width:18%;">${it.createTime?string("yyyy.MM.dd")}</div>
					<div class="part" style="width:18%;">${it.amount}</div>
					<div class="part" style="width:18%;">${it.receiptWay.label}</div>
					<div class="part" style="width:28%;">${it.bank.formatBankCard}</div>
					<div class="part" style="width:18%;">${it.status.label}</div>
				</div>
			</div>
			</#list>
		</div>
<#include "/init/pager.ftl"/>
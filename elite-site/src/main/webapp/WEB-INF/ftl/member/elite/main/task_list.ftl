    <style type="text/css">
		.tabs{
            width:100%;
            height:40px;
            text-align: center;
            font-size: 18px;
            border-bottom: 1px solid #B3B3B3;
        }
        .active-tab{
            color:#FEA600;
        }
        .tab-number{
            font-size: 14px;
            color:#FEA600;
        }

        .active-line{
            width:213px;
            border-bottom: 2px solid #FEA600;
            position: absolute;
            left:0;
            bottom: 0;
        }

        .tab{
            cursor: pointer;
        }

        .release-list{
            width:100%;
            height:188px;
            border:1px solid #E6E6E6;
            border-radius: 5px;
            margin-top:20px;
        }

        .part1{
            width:100%;
            height:40px;
            line-height: 40px;
            background-color: #F2F2F2;
            border-bottom: 1px solid #E6E6E6;
            text-align: center;
            font-size: 14px;
            color:#9B9B9B;
            border-top-left-radius: 5px;
            border-top-right-radius: 5px;
        }
        .focusprt {
        list-style:none;
        margin:0;
    }
    .focusprt li {
        display:inline-block;
        margin:0 10px;
    }
    .clock {
	  display:block;
	  width:20px;
	  height:20px;
	  margin-top:10px;
	  background:url(/res/images/clock.png) no-repeat center;
	}
    
    .focusprt li a {
        display:inline-block;
        color:#9b9b9b;
        font-size:12px;
    }
    .focusprt li span {
        width:16px;
        height:17px;
        display:inline-block;
        margin-right:5px;
        vertical-align:middle;
    }
    .focus {
        background:url(/res/images/focus.png);
    }
    .part {
        background:url(/res/images/participate.png);
    }
    .tet {
      font-size:16px;
      color:#a4a4a4a;
      margin:0 0 5px 0;
    
    }
    

        .opt-icon{
            width:20px;
            height:20px;
            cursor: pointer;
            display: inline-block;
            vertical-align: text-bottom;
        }

        .vertical-line{
            height:28px;
            border-right:1px solid #E6E6E6;
            position: absolute;
            top:5px;
            left:240px;
            
        }

        .project-detail{
            width:100%;
            height:146px;
            background-color: #FAFAFA;
            border-bottom-left-radius: 5px;
            border-bottom-right-radius: 5px;
        }

        .main-info{
            float:left;
            width:60%;
            height:146px;
            border-right: 1px solid #E6E6E6;
        }

        .project-logo{
            width:105px;
            height:105px;
            line-height: 105px;
            text-align: center;
            background-color: #F29D70;
            color:white;
            font-size: 58px;
            float:left;
        }
       
       .project-logos{
            width:105px;
            height:105px;
            line-height: 105px;
            text-align: center;
            background-color: #4198c7;
            color:white;
            font-size: 58px;
            float:left;
        }
        .project-info{
            float:left;
            margin-left:20px;
        }
        .project-name{
            display: inline-block;
            margin-right:10px;
        }
        .class-label{
            display: inline-block;
            width:40px;
            height:16px;
            line-height: 16px;
            text-align: center;
            border:1px solid #E6E6E6;
            border-radius: 10px;
            color:#9B9B9B;
            font-size: 12px;
            margin-right:5px;
        }

        .index{
            font-size: 12px;
            color:#9B9B9B;
        }

        .value{
            font-size: 12px;
        }

        .status {
            float:left;
            width:18%;
            height:146px;
            border-right: 1px solid #E6E6E6;
            font-size: 12px;
            color:#9B9B9B;
            text-align: center;
        }
        .view {
          color:#4a90e2;
          text-decoration:none;
        }
        .opt{
            float:left;
            width:22%;
            height:146px;
            background-color: #F5FBFC;
        }
       
      
        .current-status{
            font-size: 14px;
            color:#2CB7C9;
        }

        .complete-btn{
            width:90px;
            height:26px;
            background-color: #FEA600;
            border:none;
            border-radius: 14px;
            color:white;
            font-size: 14px;
        }
      .complete-btns {
            /*width:90px;
            height:26px;*/
            padding:5px 10px;
            background-color: #fea600;
            border:none;
            border-radius: 41px;
            color:white;
            font-size: 14px;
        }
        .complete-btnss {
        	background:#ccc; 
    	}
        .opt-title{
            display: inline-block;
            cursor: pointer;
        }
        
    .rcmd {
        display:block;
        padding:3px 10px;
        color:#ccc;
        font-size:14px;
        border-radius:14px;
        position:relative;
    }
    .rcmds {
    	width:90px;
    	height:26px;
    	line-height:26px;
    	margin:auto;
    	border:1px solid #fea600;
    	color:#fea600;
    	border-radius:14px;
    	
    }
    .rcmd-txt {
      display:block;
      width:14px;
      height:14px;
      border-radius:50%;
      color:#FFF;
      background-color: #fea600;
      position: absolute;
      top:-5px;
      right:30px;
    }
     .content{
          width:1000px;
          min-height:500px;
          margin-left:auto;
          margin-right:auto;
          position: relative;
      }

      .location-nav{
          padding-top:20px;
          margin-bottom: 20px;
      }
      .left-column{
          width:240px;
          border:1px solid #E6E6E6;
          border-radius: 4px;
          text-align: center;
          background-color: #FAFAFA;
          box-shadow: 2px 2px 6px 0px rgba(0,0,0,0.30);
          margin-bottom: 20px;
      }

      .column-head{
          width: 80px;
          height: 80px;
          background:url("/res/images/test.jpg") no-repeat;
          background-size: 80px 80px;
          border: 1px solid #979797;
          -moz-border-radius: 50px;
          -webkit-border-radius: 50px;
          border-radius: 50px;
      }

      .circle-box{
          padding-left:80px;
          padding-top:20px;
          position: relative;
      }

      .username{
          margin-top:14px;
      }

      .split-line{
          width:100%;
          border-bottom: 1px solid #E6E6E6;
      }
    
    </style>
<form id="taskForm">    
    <div style="position: relative;" id="my_p_tabs">
        <div class="tabs">
            <div class="col-xs-4 col-md-4 active-tab" data="recomd_me" index="0">
                <div class="tab">推荐给我的&nbsp;<span class="tab-number" id="recomtab">${recomCount}</span></div>
            </div>
            <div class="col-xs-4 col-md-4" data="under_way" index="1">
                <div class="tab">认领中的任务&nbsp;<span class="tab-number" id="recruitintab">${recruitInCount}</span></div>
            </div>
            <div class="col-xs-4 col-md-4" data="under_finish" index="2">
                <div class="tab">已认领的任务&nbsp;<span class="tab-number" id="recruitwintab">${recruitWinCount}</span></div>
            </div>
        </div>
        <div class="active-line"></div>
    </div>
    <div id="dataList"></div>
    <input type="hidden" id="taskType" name="type" />
</form>    
    
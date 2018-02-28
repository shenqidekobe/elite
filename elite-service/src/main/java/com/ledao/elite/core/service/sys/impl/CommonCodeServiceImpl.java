package com.ledao.elite.core.service.sys.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ledao.elite.core.domain.sys.CommonCode;
import com.ledao.elite.core.repository.sys.CommonCodeRepository;
import com.ledao.elite.core.service.sys.CommonCodeService;

@Service
public class CommonCodeServiceImpl implements CommonCodeService{
	
	@Resource
	private CommonCodeRepository commonCodeRepository;

	@Transactional
	@Override
	public String disposeOddNumber(String func, String preStr, String patten, Integer glideLen, String sperator) {
		return fetchNumber(func,preStr,patten,glideLen,sperator);
	}
	
	
	/**
	 * 系统编码统一生成
	 * @param params
	 * @param page
	 * @return
	 */
	private String fetchNumber(String func,String preStr,String patten,Integer glideLen,String sperator) {
		String code  = "";  //编码
		String dateStr =StringUtils.isEmpty(patten)?"":DateFormatUtils.format(new Date(),patten);  //当前date  yyyyMMdd
		int glideTime = glideLen;
		
		CommonCode pcc = new CommonCode();
		pcc.setPreVal(preStr);
		pcc.setFuncCode(func);
		pcc.setFlag(0);
		
		List<CommonCode> pccList = commonCodeRepository.queryCommonCodeList(pcc);//加锁
		if(!pccList.isEmpty()){
			pcc = pccList.get(0);
			String glide = pcc.getGlide();     //流水号
			
			String aldate = glide.substring(0, dateStr.length());
			glide = glide.substring(dateStr.length());
			int alGlideTime = glide.length();
			if(aldate.equals(dateStr)&&glideTime==alGlideTime){
				long glidenum = Long.parseLong(glide)+1;
				String lshao = String.format("%0"+glideTime+"d", glidenum); 
				code =preStr+dateStr+lshao;
				pcc.setGlide(dateStr+lshao);
			}else {
				String lshao = String.format("%0"+glideTime+"d", 1); 
				code =preStr+dateStr+lshao;
				pcc.setGlide(dateStr+String.format("%0"+glideTime+"d", 2));
			}
			commonCodeRepository.updateCommonCode(pcc);
		}else {
			pcc = new CommonCode();
			pcc.setFuncCode(func);
			pcc.setFlag(0);
			pcc.setPreVal(preStr);
			String lshao = String.format("%0"+glideTime+"d", 1); 
			code =preStr+dateStr+lshao;
			pcc.setGlide(dateStr+String.format("%0"+glideTime+"d", 2));
			commonCodeRepository.save(pcc);
		}
		return code;
	}
	

}

package com.ledao.elite.manager.test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ledao.elite.core.service.sys.CommonCodeService;

/**
 * 系统编码并发测试
 */
/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:app.xml")*/
public class CommonCodeConcurrentTest {

	/*@Resource
	private CommonCodeService commonCodeService;

	private static Integer count = 1000;// 现成数量

	private CyclicBarrier cyclicBarrier = new CyclicBarrier(count);// 并发执行控制

	private Set<String> sets = new HashSet<>();

	*//**
	 * 批量生成系统编码测试
	 *//*
	@Rollback
	@Test
	public void batchCreateCommonCode() {
		ExecutorService executorService = Executors.newFixedThreadPool(count);
		for (int i = 0; i < count; i++)
			executorService.execute(new CreateSysCode(cyclicBarrier));
		executorService.shutdown();
		while (!executorService.isTerminated()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	
	//任务类
	public class CreateSysCode implements Runnable {
		private CyclicBarrier cyclicBarrier;

		public CreateSysCode(CyclicBarrier cyclicBarrier) {
			this.cyclicBarrier = cyclicBarrier;
		}

		public void run() {
			try {
				cyclicBarrier.await();// 等待所有任务准备就绪

				String code = commonCodeService.disposeOddNumber("orderID", "T", "yyyyMMdd", 6, null);
				sets.add(code);
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}*/

}

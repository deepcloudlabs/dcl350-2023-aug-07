package com.example.lottery.service.business;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.lottery.service.LotteryService;

@Service
public class StandardLotteryService implements LotteryService {
	private final int lotteryMax;
	private final int lotterySize;

	public StandardLotteryService(
			@Value("${lotteryMax}") int lotteryMax, 
			@Value("${lotterySize}") int lotterySize) {
		this.lotteryMax = lotteryMax;
		this.lotterySize = lotterySize;
	}

	@Override
	public List<List<Integer>> getLotteryNumbers(int column) {
		return IntStream.range(0, column)
				        .mapToObj(i -> getLotteryNumbers())
				        .toList();
	}

	private List<Integer> getLotteryNumbers(){
		return ThreadLocalRandom.current().ints(1, lotteryMax)
										  .distinct()
				                          .limit(lotterySize)
				                          .sorted()
				                          .boxed()
				                          .toList();
	}
}

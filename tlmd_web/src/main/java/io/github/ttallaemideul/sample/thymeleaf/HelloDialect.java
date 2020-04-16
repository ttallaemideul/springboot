package io.github.ttallaemideul.sample.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;

public class HelloDialect extends AbstractProcessorDialect {
	private static final String PREFIX = "hello";
	
	public HelloDialect() {
		super("Hello Dialect" // dialect name
				, PREFIX// 접두사. 속성 사용방법: hello:*
				, 1000 // dialect 우선순위
				);
	}

	/**
	 * dialect 처리기를 초기화 한다.
	 */
	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processors = new HashSet<>();
		processors.add(new SayToAttrTagProcessor(dialectPrefix));
		return processors;
	}

}

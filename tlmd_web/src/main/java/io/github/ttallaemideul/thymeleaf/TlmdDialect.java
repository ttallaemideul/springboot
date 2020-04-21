package io.github.ttallaemideul.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

public class TlmdDialect extends AbstractProcessorDialect {
	private static final String PREFIX = "tlmd";
	
	public TlmdDialect() {
		super("Ttallaemideul Dialect" // dialect name
				, PREFIX	// 접두사. 속성 사용방법: tlmd:*
				, StandardDialect.PROCESSOR_PRECEDENCE 		// dialect 우선순위
				);
	}

	/**
	 * dialect 처리기를 초기화 한다.
	 */
	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processors = new HashSet<>();
		processors.add(new OxAttrTagProcessor(dialectPrefix));
		processors.add(new PagingElementTagProcessor(dialectPrefix));
		return processors;
	}

}

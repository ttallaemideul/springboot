package io.github.ttallaemideul.sample.thymeleaf;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;
import org.unbescape.html.HtmlEscape;

public class SayToAttrTagProcessor extends AbstractAttributeTagProcessor {

	private static final String ATTR_NAME = "sayto";
	private static final int PRECEDENCE = 10000;
	protected SayToAttrTagProcessor(String dialectPrefix) {
		super(
				TemplateMode.HTML		// HTML 모드로 설정
				, dialectPrefix			// 접두사
				,null					// 태그명 미설정. 적용가능 태그명에 제약이 없다.
				,false					// 태그명에 접두사를 적용하지 않는다.
				,ATTR_NAME				// 적용될 속성명
				,true					// 속성명에 dialect 접두사를 사용한다.
				,PRECEDENCE				// dialect 들 내에서 적용 우선권.
				,true					// 적용한 속성명은 처리 후 삭제한다.
		);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
			String attributeValue, IElementTagStructureHandler structureHandler) {
		structureHandler.setBody("Hello, " + HtmlEscape.escapeHtml5(attributeValue) + "!", false);
	}

}

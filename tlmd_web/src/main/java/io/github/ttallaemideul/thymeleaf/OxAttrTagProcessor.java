package io.github.ttallaemideul.thymeleaf;

import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IAttribute;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;

import io.github.ttallaemideul.utils.UtilString;

/**
 * 속성의 입력값이 true에 해당하면 '●', false 이면 '○'을 출력한다.
 * 
 * @author TtalLaeMiDeul
 *
 */
public class OxAttrTagProcessor extends AbstractAttributeTagProcessor {

	private static final String ATTR_NAME = "ox";
	private static final int PRECEDENCE = 10000;
	private static final String YES = "●";
	private static final String NO = "○";
	private static final String CLS_YES = "w3-text-blue";
	private static final String CLS_NO = "w3-text-gray";
	private static final String CLS = "class";

	protected OxAttrTagProcessor(String dialectPrefix) {
		super(TemplateMode.HTML // HTML 모드로 설정
				, dialectPrefix // 접두사
				, null // 태그명 미설정. 적용가능 태그명에 제약이 없다.
				, false // 태그명에 접두사를 적용하지 않는다.
				, ATTR_NAME // 적용될 속성명
				, true // 속성명에 dialect 접두사를 사용한다.
				, PRECEDENCE // dialect 들 내에서 적용 우선권.
				, true // 적용한 속성명은 처리 후 삭제한다.
		);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
			String attributeValue, IElementTagStructureHandler structureHandler) {
		// 속성값을 타임리프 표준 표현식으로 파싱한 후 표현식을 처리한다.
		final IEngineConfiguration configuration = context.getConfiguration();
		final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
		final IStandardExpression expression = parser.parseExpression(context, attributeValue);
		final Object attrVal = expression.execute(context);
		final IAttribute cls = tag.getAttribute(CLS);
		String oldCls = null;
		if (cls != null) {
			oldCls = cls.getValue(); // 기존 class 값.
		}
		// 문자열이 true에 해당하는지 판단.
		if (UtilString.isTrue(attrVal)) {
			if (oldCls != null) {
				// 기존 클래스에 추가
				structureHandler.setAttribute(CLS, oldCls + " " + CLS_YES);
			} else {
				structureHandler.setAttribute(CLS, CLS_YES);
			}
			structureHandler.setBody(YES, false);
		} else {
			if (oldCls != null) {
				structureHandler.setAttribute(CLS, oldCls + " " + CLS_NO);
			} else {
				structureHandler.setAttribute(CLS, CLS_NO);
			}
			structureHandler.setBody(NO, false);
		}
	}

}

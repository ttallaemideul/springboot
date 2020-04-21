package io.github.ttallaemideul.thymeleaf;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IOpenElementTag;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

import io.github.ttallaemideul.utils.UtilString;

/**
 * paging 태그를 처리한다. 한 페이지당 rows 만큼 표시하는 페이징 태그를 생성한다.
 * 
 * @author TtalLaeMiDeul
 *
 */
public class PagingElementTagProcessor extends AbstractElementTagProcessor {
	private static final String TAG_NAME = "paging";
	private static final int PRECEDENCE = 10000;
	private static final int SHOWPAGES = 5; // 몇 페이지씩 한 화면에 그릴지

	protected PagingElementTagProcessor(String dialectPrefix) {
		super(TemplateMode.HTML // HTML 모드로 설정
				, dialectPrefix // 접두사
				, TAG_NAME // 태그명
				, true // 태그명에 접두사를 적용
				, null // 적용될 속성명은 없다
				, false // 속성명에 접두사 없다
				, PRECEDENCE // dialect 들 내에서 적용 우선권.
		);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
			IElementTagStructureHandler structureHandler) {
		// 현재 페이지
		final int page = UtilString.parseInt(tag.getAttributeValue("page"), 1);
		// 페이지당 레코드 수
		final int rows = UtilString.parseInt(tag.getAttributeValue("rows"), 10);
		// 전체 레코드 수
		final int total = UtilString.parseInt(tag.getAttributeValue("total"), 10);
		// 전체 페이지 수
		final int totPage = total / rows + (total > total / rows * rows ? 1 : 0);
		// 표시할 최소/최대 페이지
		int minPage = page / SHOWPAGES * SHOWPAGES + 1;
		if (minPage > page) {
			minPage = (page - 1) / SHOWPAGES * SHOWPAGES + 1;
		}
		int maxPage = minPage + SHOWPAGES;
		if (maxPage > totPage) {
			maxPage = totPage + 1;
		}
		// paging을 표시할 DOM을 만든다.
		final IModelFactory modelFactory = context.getModelFactory();

		final IModel model = modelFactory.createModel();
		model.add(modelFactory.createComment("tlmd:paging 태그로 생성된 paging 태그"));
		model.add(modelFactory.createOpenElementTag("div", "class", "w3-center w3-padding-32"));
		model.add(modelFactory.createOpenElementTag("div", "class", "w3-bar"));
		if (minPage > SHOWPAGES) {
			IOpenElementTag aTag = modelFactory.createOpenElementTag("a", "class", "w3-button w3-hover-black");
			aTag = modelFactory.setAttribute(aTag, "href",
					String.format("javascript:tlmd_gfnReloadUrl('page',%d);", minPage - 1));
			model.add(aTag);
			model.add(modelFactory.createText("◀"));
			model.add(modelFactory.createCloseElementTag("a"));
		}
		for (int i = minPage; i < maxPage; i++) {
			IOpenElementTag aTag = modelFactory.createOpenElementTag("a");
			if (i == page) {
				aTag = modelFactory.setAttribute(aTag, "class", "w3-button w3-black");
			} else {
				aTag = modelFactory.setAttribute(aTag, "class", "w3-button w3-hover-black");
			}
			aTag = modelFactory.setAttribute(aTag, "href",
					String.format("javascript:tlmd_gfnReloadUrl('page',%d);", i));
			model.add(aTag);
			model.add(modelFactory.createText("" + i));
			model.add(modelFactory.createCloseElementTag("a"));
		}
		if (maxPage < totPage) {
			IOpenElementTag aTag = modelFactory.createOpenElementTag("a", "class", "w3-button w3-hover-black");
			aTag = modelFactory.setAttribute(aTag, "href",
					String.format("javascript:tlmd_gfnReloadUrl('page',%d);", maxPage));
			model.add(aTag);
			model.add(modelFactory.createText("▶"));
			model.add(modelFactory.createCloseElementTag("a"));
		}
		model.add(modelFactory.createCloseElementTag("div"));
		model.add(modelFactory.createCloseElementTag("div"));

		// 타임리프 엔진에 현재 엘리멘트를 새로 만든 모델로 교체하도록 한다.
		structureHandler.replaceWith(model, false);
	}

}

package com.ll.exam.article;

import com.ll.exam.Rq;
import com.ll.exam.article.dto.ArticleDto;

import java.util.List;

public class ArticleController {
    private ArticleService articleService;

    public ArticleController() {
        articleService = new ArticleService();
    }

    public void showList(Rq rq) {
        List<ArticleDto> articleDtos = articleService.findAll();

        rq.setAttr("articles", articleDtos);
        rq.view("usr/article/list");
    }

    public void showWrite(Rq rq) {
        rq.view("usr/article/write");
    }

    public void write(Rq rq) {

        String title = rq.getParam("title", "");
        String body = rq.getParam("body", "");

        long id = articleService.write(title, body);
        rq.replace("/usr/article/list/free", "%d번 게시물이 생성되었습니다.".formatted(id));
    }

    public void showDetail(Rq rq) {
        long id = rq.getLongPathValueByIndex(1, 0);

        if (id == 0) {
            rq.historyBack("번호를 입력해주세요");
            return;
        }

        ArticleDto articleDto = articleService.findById(id);

        if (articleDto == null) {
            rq.historyBack("해당 글이 존재하지 않습니다.");
            return;
        }

        rq.setAttr("article", articleDto);
        rq.view("usr/article/detail");
    }

    public void delete(Rq rq) {
        long id = rq.getLongPathValueByIndex(1, 0);

        if (id == 0) {
            rq.historyBack("번호를 입력해주세요");
            return;
        }

        ArticleDto articleDto = articleService.findById(id);
        if (articleDto == null) {
            rq.historyBack("해당 글이 존재하지 않습니다.");
            return;
        }

        articleService.delete(id);
        rq.replace("/usr/article/list/free", "<div>%d번 게시물이 삭제되었습니다.</div>".formatted(id));
    }

    public void modify(Rq rq) {
        long id = rq.getLongPathValueByIndex(1, 0);
        String title = rq.getParam("title", "");
        String body = rq.getParam("body", "");

        if (id == 0) {
            rq.historyBack("번호를 입력해주세요.");
            return;
        }

        ArticleDto articleDto = articleService.findById(id);

        if (articleDto == null) {
            rq.historyBack("해당 글이 존재하지 않습니다.");
            return;
        }

        articleService.modify(id, title, body);

        rq.replace("/usr/article/detail/free/%d".formatted(id), "%d번 게시물이 수정되었습니다.".formatted(id));
    }

    public void showModify(Rq rq) {
        long id = rq.getLongPathValueByIndex(1, 0);

        if (id == 0) {
            rq.historyBack("번호를 입력해주세요.");
            return;
        }

        ArticleDto articleDto = articleService.findById(id);

        if (articleDto == null) {
            rq.historyBack("해당 글이 존재하지 않습니다.");
            return;
        }

        rq.setAttr("article", articleDto);
        rq.view("usr/article/modify");
    }

    public void getArticles(Rq rq) {
        long fromId = rq.getLongParam("fromId", -1);

        List<ArticleDto> articleDtos = null;

        if ( fromId == -1 ) {
            articleDtos = articleService.findAll();
        }
        else {
            articleDtos = articleService.findIdGreaterThan(fromId);
        }

        rq.successJson(articleDtos);
    }

    public void showListAuto(Rq rq) {
        rq.view("usr/article/listAuto");
    }
}

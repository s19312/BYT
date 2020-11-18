package com.company;

import java.util.ArrayList;

public class MementoPattern {

    public static void main(String[] args) {
        ArticleCaretaker caretaker = new ArticleCaretaker();
        ArticleOriginator originator = new ArticleOriginator();
        originator.setArticle("something1","content number1");
        originator.setArticle("something1","content number2");
        caretaker.addMemento( originator.save() );
        originator.setArticle("something1","content number3");
        caretaker.addMemento( originator.save() );
        originator.setArticle("something1","content number6");
        caretaker.addMemento( originator.save() );
        originator.restore( caretaker.getArticleMemento(1) );
    }
}
class ArticleMemento {
    private String tittle;
    private String content;

    public ArticleMemento(String tittle,String content) {
        this.tittle = tittle;
        this.content = content;
    }

    public String getTittle() {
        return tittle;
    }
    public String getContent() {
        return content;
    }
}

class ArticleOriginator {
    private String tittle;
    private String content;

    public void setArticle(String tittle,String content) {
        System.out.println("Originator: Setting Article to '" + tittle + "' '" + content + "'");
        this.tittle =tittle;
        this.content =content;
    }

    public ArticleMemento save() {
        System.out.println("Originator: Saving to Memento.");
        return new ArticleMemento(tittle,content);
    }
    public void restore(ArticleMemento a) {
        tittle = a.getTittle();
        content = a.getContent();
        System.out.println("Originator: State after restoring from Memento:  tittle:" + a.getTittle() + " content : " + a.getContent());
    }

    @Override
    public String toString() {
        return "ArticleOriginator{" +
                "tittle='" + tittle + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

class ArticleCaretaker {
    private ArrayList<ArticleMemento> mementos = new ArrayList<>();

    public void addMemento(ArticleMemento m) {
        mementos.add(m);
    }

    public ArticleMemento getArticleMemento(int index) {
        return mementos.get(index);
    }
}

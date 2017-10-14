package com.example.caiowillian.bitseeks.br.com.fiap.models;

/**
 * Created by Caio Willian on 13/10/2017.
 */

public class News {
    private int id;
    private String title;
    private String description;
    private String body;
    private String dataCadastro;
    private String dataAlteracao;
    private ImgNews imgNews;

    public News(){}

    public News(int id, String title, String description, String body, String dataCadastro, String dataAlteracao, ImgNews imgNews) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.body = body;
        this.dataCadastro = dataCadastro;
        this.dataAlteracao = dataAlteracao;
        this.imgNews = imgNews;
    }

    public ImgNews getImgNews() {
        return imgNews;
    }

    public void setImgNews(ImgNews imgNews) {
        this.imgNews = imgNews;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(String dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
}

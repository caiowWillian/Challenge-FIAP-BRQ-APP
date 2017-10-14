package com.example.caiowillian.bitseeks.br.com.fiap.models;

/**
 * Created by Caio Willian on 13/10/2017.
 */

public class ImgNews {
    public ImgNews(){}

    public ImgNews(int id, String mimeType, String fileName, byte[] fileContent, int fileLenght) {
        this.id = id;
        this.mimeType = mimeType;
        this.fileName = fileName;
        this.fileContent = fileContent;
        this.fileLenght = fileLenght;
    }

    private int id;
    private String mimeType;
    private String fileName;
    private byte[] fileContent;
    private int fileLenght;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        fileContent = fileContent;
    }

    public int getFileLenght() {
        return fileLenght;
    }

    public void setFileLenght(int fileLenght) {
        this.fileLenght = fileLenght;
    }
}

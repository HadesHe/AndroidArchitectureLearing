package com.example.zhanghehe.androidarchitecturelearing.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.example.zhanghehe.androidarchitecturelearing.model.Comment;

import java.util.Date;

@Entity(tableName = "comments",
        foreignKeys = {
            @ForeignKey(entity = ProductEntity.class,
                        parentColumns = "id",
                        childColumns = "productId",
                        onDelete = ForeignKey.CASCADE)},
        indices = {@Index(value = "productId")}
        )
public class CommentEntity implements Comment {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int productId;
    private String text;
    private Date postAt;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Date getPostedAt() {
        return postAt;
    }

    public void setPostAt(Date postAt) {
        this.postAt = postAt;
    }

    public CommentEntity(){}

    public CommentEntity(int id, int productId, String text, Date postAt) {
        this.id = id;
        this.productId = productId;
        this.text = text;
        this.postAt = postAt;
    }
}

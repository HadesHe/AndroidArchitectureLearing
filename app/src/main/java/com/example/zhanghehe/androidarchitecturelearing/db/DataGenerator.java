package com.example.zhanghehe.androidarchitecturelearing.db;

import com.example.zhanghehe.androidarchitecturelearing.db.entity.CommentEntity;
import com.example.zhanghehe.androidarchitecturelearing.db.entity.ProductEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DataGenerator {
    private static final String[] FIRST=new String[]{
      "Special edition","New","Cheap","Quality","Used"
    };

    private static final String[] SECOND=new String[]{
      "Three-headed Monkey","Rubber Chicken","Pint of Grog","Monocle"
    };

    private static final String[] DESCRIPTION=new String[]{
      "is finally here","is recommended by Stan S. Stanman",
      "is the best sold product on Melee Island","is \uD83D\uDCAF","is Love","is fine"
    };
    private static final String[] COMMENTS=new String[]{
      "Comment 1","Comment 2","Comment 3","Comment 4","Comment 5","Comment 6"
    };


    public static List<ProductEntity> generateProducts() {
        List<ProductEntity> products=new ArrayList<>(FIRST.length*SECOND.length);
        Random random=new Random();
        for (int i = 0; i <FIRST.length; i++) {
            for (int j = 0; j < SECOND.length; j++) {
                ProductEntity product=new ProductEntity();
                product.setName(FIRST[i]+" "+SECOND[j]);
                product.setDescription(product.getName()+" "+DESCRIPTION[j]);
                product.setPrice(random.nextInt(240));
                product.setId(FIRST.length*i+j+1);
                products.add(product);
            }
        }
        return products;
    }

    public static List<CommentEntity> generateCommentsForProducts(final List<ProductEntity> products){
        List<CommentEntity> comments=new ArrayList<>();
        Random random=new Random();

        for (ProductEntity product : products) {
            int commentsNumber=random.nextInt(5)+1;

            for (int i = 0; i < commentsNumber; i++) {
                CommentEntity commentEntity=new CommentEntity();
                commentEntity.setProductId(product.getId());
                commentEntity.setText(COMMENTS[i]+" for "+product.getName());
                commentEntity.setPostAt(new Date(System.currentTimeMillis()- TimeUnit.DAYS.toMillis(commentsNumber-i)+TimeUnit.HOURS.toMillis(i)));
                comments.add(commentEntity);

            }
        }
        return comments;
    }
}





































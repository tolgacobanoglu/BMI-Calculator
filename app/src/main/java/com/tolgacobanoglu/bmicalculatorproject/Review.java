package com.tolgacobanoglu.bmicalculatorproject;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.Task;

/**
 * It will work for activity
 * This structure has a quota and if over quota it wont be work for quota https://developer.android.com/guide/playcore/in-app-review#quotas
 */
public class Review {

    //iki görevi var inceleme bilgilerini almak ve diğeri inceleme akışını başlatmak
    private final ReviewManager reviewManager;

    public Review(Context context) {
        this.reviewManager = ReviewManagerFactory.create(context);
    }

    public void doRate(Context context) {
        //request asenkron bir çağrı,
        Task <ReviewInfo> request = reviewManager.requestReviewFlow();
        //asenkron olduğu için tamamlanmasını beklemeleyiz
        request.addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                //inceleme akışını başlatmak için kullanılan gerekli bilgileri tutan sınıf
                ReviewInfo reviewInfo = task.getResult();
                Task <Void> flow = reviewManager.launchReviewFlow((Activity) context, reviewInfo);
                flow.addOnCompleteListener(task1 -> Toast.makeText(context, "Flow finished", Toast.LENGTH_SHORT).show());
            }
        });
    }
}

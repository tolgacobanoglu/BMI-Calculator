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
 */
public class Review {

    private final ReviewManager reviewManager;

    public Review(Context context) {
        this.reviewManager = ReviewManagerFactory.create(context);
    }

    public void doRate(Context context) {
        Task <ReviewInfo> request = reviewManager.requestReviewFlow();
        request.addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                ReviewInfo reviewInfo = task.getResult();
                Task <Void> flow = reviewManager.launchReviewFlow((Activity) context, reviewInfo);
                flow.addOnCompleteListener(task1 -> Toast.makeText(context, "Flow finished", Toast.LENGTH_SHORT).show());
            }
        });
    }
}

package com.example.sheetmusiclist.dto.review;


import com.example.sheetmusiclist.entity.review.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewFindResponseDto {

    private String comment;
    private Integer rate;

    public static ReviewFindResponseDto toDto(Review review) {
        return new ReviewFindResponseDto(review.getComment(), review.getRate());
    }
}

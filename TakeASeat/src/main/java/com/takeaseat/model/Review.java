package com.takeaseat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

import static com.takeaseat.constants.StringConstants.REVIEWS;
import static com.takeaseat.constants.StringConstants.REVIEW_ID_COLUMN_NAME;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = REVIEWS)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = REVIEW_ID_COLUMN_NAME)
    private Long id;

    @NonNull
    private String comment;
    @NonNull
    private Integer grade;
    @NonNull
    private String user;
    @NonNull
    private LocalDate localDate;
}

package com.telegram.rtb.model.domain.keyword;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;


@Entity
@DiscriminatorValue("MINUS")
@SuperBuilder
@AllArgsConstructor
public class MinusKeyword extends Keyword {
}

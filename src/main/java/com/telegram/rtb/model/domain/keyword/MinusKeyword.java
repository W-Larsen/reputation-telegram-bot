package com.telegram.rtb.model.domain.keyword;

import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MINUS")
@SuperBuilder
@AllArgsConstructor
public class MinusKeyword extends Keyword {
}

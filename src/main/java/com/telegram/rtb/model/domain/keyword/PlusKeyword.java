package com.telegram.rtb.model.domain.keyword;

import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PLUS")
@SuperBuilder
@AllArgsConstructor
public class PlusKeyword extends Keyword {
}

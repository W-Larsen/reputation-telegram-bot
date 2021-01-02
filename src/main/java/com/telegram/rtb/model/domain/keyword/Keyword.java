package com.telegram.rtb.model.domain.keyword;

import com.telegram.rtb.model.domain.key.KeywordPk;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Keywords entity.
 */
@Entity
@Table(name = "keywords")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "keyword_type", discriminatorType = DiscriminatorType.STRING)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Keyword implements Serializable {

    @EmbeddedId
    private KeywordPk keywordPk;
    private String keywordValue;
}

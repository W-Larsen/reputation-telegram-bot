package com.telegram.rtb.model.domain.keyword;

import com.telegram.rtb.model.domain.key.KeywordPk;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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

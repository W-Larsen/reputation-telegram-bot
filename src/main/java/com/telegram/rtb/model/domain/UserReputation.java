package com.telegram.rtb.model.domain;

import com.telegram.rtb.model.domain.key.UserReputationPk;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;

/**
 * User reputation model.
 */
@Entity
@Table(name = "user_reputations")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserReputation {

    @EmbeddedId
    private UserReputationPk userReputationPk;
    private Long reputationValue;
    private ZonedDateTime updatedDateTime;
    private Long updatedFromId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Getter
    @Setter
    private TelegramUser telegramUser;

    @ManyToOne
    @JoinColumn(name = "chat_id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Getter
    @Setter
    private TelegramChat telegramChat;
}

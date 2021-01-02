package com.telegram.rtb.model.keyword;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllKeywords {
    private Long chatId;
    private PlusKeywords plusKeywords;
    private MinusKeywords minusKeywords;
}

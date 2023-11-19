package ru.vlasov.taskplannermailservicemvn.dto;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class MessageDto {

    private String email;

    private String title;

    private String body;
}

package com.example.busyshop.dto.responsedto;


import com.example.busyshop.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CustomerResponseDto {

    String name;

    String email;

    String phone;

    Gender gender;
}

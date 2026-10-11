/**
 * handle incoming post and put requests from frontend
 * requests.
 * V 11.10.26
 */
package shenefelt.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectRequestDTO {
    private String name;
    private String description;
    private Long customerId;
    private Long userId;
}
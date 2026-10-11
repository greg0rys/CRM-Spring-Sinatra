package shenefelt.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter;
@Setter;
public class ProjectResponseDTO {
    private Long id;
    private String name;
    private String description;

    // fk fields
    private Long customerId;
    private Long userId;
    private String customerName;
    private String userName;

    /**
     * create a response DTO from the Project Entity with relations attached
     */
    public static ProjectResponseDTO fromEntity(Project project)
    {
        ProjectResponseDTO dto = new ProjectResponseDTO();

        // set basic project model info for res
        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setDescription(project.getDescription());

        // grab customer
        Customer customer = (project.getCustomer())
        // grab user
        User user = (project.getUser());

        if(customer != null)
        {
            dto.setCustomerId(customer.getId());
            dto.setCustomerName(customer.getName());
            
        }

        if (user != null)
        {
            dto.setUserId(user.getId());
            dto.setUserName(user.getName());
        }

        return dto; // return the packaged response

    }
}
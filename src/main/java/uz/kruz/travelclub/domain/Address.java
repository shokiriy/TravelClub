package uz.kruz.travelclub.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String streetAddress;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AddressType addressType;
    @Column
    private String zipCode;
    @Column
    private String zipAddress;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private CommunityMember member;
}
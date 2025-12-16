package com.gymruts.data.entity.routine;

import com.gymruts.data.entity.routineCreation.RoutineCreation;
import com.gymruts.data.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Routine {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routine_id")
    private Long routineId;

    private String routineName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @OneToMany(mappedBy = "routine", cascade={CascadeType.REMOVE}, orphanRemoval=true)
	private List<RoutineCreation> routineCreation;
    
    public Routine( User user, String routineName) {
        this.routineName = routineName;
        this.user = user;
    }

}


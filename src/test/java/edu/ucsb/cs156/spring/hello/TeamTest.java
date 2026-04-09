package edu.ucsb.cs156.spring.hello;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TeamTest {

    Team team;

    @BeforeEach
    public void setup() {
        team = new Team("test-team");
    }

    @Test
    public void getName_returns_correct_name() {
        assert (team.getName().equals("test-team"));
    }

    @Test
    public void toString_returns_correct_string() {
        assertEquals("Team(name=test-team, members=[])", team.toString());
    }

    @Test
    public void equals_returns_true_for_the_same_object() {
        Team other = team;
        assertTrue(team.equals(other), "Same object should be equal to itself");
    }

    @Test
    public void equals_returns_false_for_instance_of_different_class() {
        class OtherClass {
            String body = "body";
        }
        OtherClass other = new OtherClass();
        assertFalse(team.equals(other), "Instances of different classes should not be equal");
    }

    @Test
    public void equals_returns_true_for_objects_with_equal_name_and_members() {
        ArrayList<String> members = new ArrayList<>(List.of("Alice", "Bob", "Charlie"));
        team.setMembers(members);

        Team same = new Team(team.getName());
        same.setMembers(members);

        Team diffName = new Team("other");
        diffName.setMembers(members);

        Team diffMembers = new Team(team.getName());
        diffMembers.setMembers(new ArrayList<>(List.of("NotAlice", "NptBob", "NotCharlie")));

        Team diffAll = new Team("other");
        diffAll.setMembers(new ArrayList<>(List.of("NotAlice", "NptBob", "NotCharlie")));

        // team + same
        assertEquals(team.getName(), same.getName());
        assertEquals(team.getMembers(), same.getMembers());
        assertTrue(team.equals(same));

        // team + diffName
        assertNotEquals(team.getName(), diffName.getName());
        assertEquals(team.getMembers(), diffName.getMembers());
        assertFalse(team.equals(diffName));

        // team + diffMembers
        assertEquals(team.getName(), diffMembers.getName());
        assertNotEquals(team.getMembers(), diffMembers.getMembers());
        assertFalse(team.equals(diffMembers));

        // team + diffAll
        assertNotEquals(team.getName(), diffAll.getName());
        assertNotEquals(team.getMembers(), diffAll.getMembers());
        assertFalse(team.equals(diffAll));
    }

    @Test
    public void hashCode_returns_true_for_equal_ogjects() {
        Team same = new Team(team.getName());
        ArrayList<String> members = new ArrayList<>(List.of("Alice", "Bob", "Charlie"));
        team.setMembers(members);
        same.setMembers(members);
        assertTrue(team.equals(same));
        assertEquals(team.hashCode(), same.hashCode());

        Team t = new Team("test");
        int result = t.hashCode();
        int expectedResult = 3556499; // hard-coded hash matching the object
        assertEquals(result, expectedResult);
    }
}

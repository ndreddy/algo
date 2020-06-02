package streams;


import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CommonInTwoSets {

    private HashMap<SkillGroup, Object> skillGroupListeners;
    private HashMap<String, Agent> agentMap;

    private void handleSkillGroupUpdate(Integer skillExt) {

        System.out.println("Handling skill group update for " + skillExt.toString());

        Set<String> skillAgentIds = agentMap.values().stream()
                .filter(x -> x.hasSkill(skillExt))
                .map(Agent::getLoginId)
                .collect(Collectors.toSet());
        System.out.println("Agents in the skill group = " + skillAgentIds.size());

    }

    public void handleConfigAgentEvent(Collection<Agent> agents) {

        System.out.println("Handling config agents =" + agents.size());
        Set<Integer> monitoredSkillNumbers = skillGroupListeners.keySet().stream()
                .map(SkillGroup::getSkillExtension)
                .map(Integer::valueOf)
                .collect(Collectors.toSet());
        System.out.println("Monitored skills =" + monitoredSkillNumbers.size());

        Set<String> monitoredAgentIds = agents.stream()
                .filter(x -> x.getSkills().stream()
                        .anyMatch(monitoredSkillNumbers::contains))
                .map(Agent::getLoginId)
                .collect(Collectors.toSet());
        System.out.println("Monitored agents =" + monitoredAgentIds.size());
    }

    private class SkillGroup {
        public String getSkillExtension() {
            return "";
        }
    }

    private class Agent {
        Set<String> skills = new HashSet<>();
        private String loginId;

        public Collection<String> getSkills() {
            return skills;
        }

        public String getLoginId() {
            return this.loginId;

        }

        public boolean hasSkill(Integer skillExt) {
            return false;
        }
    }

}

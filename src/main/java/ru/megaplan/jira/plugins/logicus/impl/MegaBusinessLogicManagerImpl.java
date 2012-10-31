package ru.megaplan.jira.plugins.logicus.impl;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.util.lang.Pair;
import com.atlassian.jira.workflow.JiraWorkflow;
import com.atlassian.plugin.util.collect.Function;
import com.opensymphony.workflow.loader.StepDescriptor;
import ru.megaplan.jira.plugins.logicus.MegaBusinessLogicManager;

import java.util.HashSet;
import java.util.Set;


/**
 * Created with IntelliJ IDEA.
 * User: Firfi
 * Date: 6/16/12
 * Time: 5:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class MegaBusinessLogicManagerImpl implements MegaBusinessLogicManager {


    /**
     * @return Function that accepts Issue and decides closed it or not
     */
    @Override
    public Function<Issue, Boolean> getIssueIsClosed() {
        Function<Issue, Boolean> issueIsClosed = new Function<Issue, Boolean>() {

            Set<String> closedStatusesNames = new HashSet<String>();

            {
                closedStatusesNames.add("Закрыто");
                closedStatusesNames.add("Closed");
            }

            Set<String> closedStatusesIcons = new HashSet<String>();

            {
                closedStatusesIcons.add("/images/icons/status_closed.gif");
            }

            /**
             * @param issue that we ought to check for closed status
             * @return true or false
             */
            @Override
            public Boolean get(Issue issue) {
                JiraWorkflow workflow =
                        ComponentAccessor.getWorkflowManager().getWorkflow(issue);
                StepDescriptor step = workflow.getLinkedStep(issue.getStatusObject());
                if (step != null && step.getActions().isEmpty()) return Boolean.TRUE;
                if (closedStatusesIcons.contains(issue.getStatusObject().getIconUrlHtml())) return Boolean.TRUE;
                if (closedStatusesNames.contains(issue.getStatusObject().getName())) return Boolean.TRUE;
                return Boolean.FALSE;
            }
        };
        return issueIsClosed;
    }
}

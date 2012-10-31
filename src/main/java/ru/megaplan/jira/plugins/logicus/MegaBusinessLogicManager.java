package ru.megaplan.jira.plugins.logicus;

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.util.lang.Pair;
import com.atlassian.plugin.util.collect.Function;


/**
 * Created with IntelliJ IDEA.
 * User: Firfi
 * Date: 6/16/12
 * Time: 5:46 PM
 * To change this template use File | Settings | File Templates.
 */
public interface MegaBusinessLogicManager {
    Function<Issue, Boolean> getIssueIsClosed();
}

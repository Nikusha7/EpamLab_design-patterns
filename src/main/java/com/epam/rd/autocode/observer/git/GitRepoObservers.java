package com.epam.rd.autocode.observer.git;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class GitRepoObservers {
    private static final Logger logger = Logger.getLogger(GitRepository.class.getName());

    public static Repository newRepository() {
        return new GitRepository();
    }

    public static WebHook mergeToBranchWebHook(String branchName) {
        return new MergeWebHook(branchName);
    }

    public static WebHook commitToBranchWebHook(String branchName) {
        return new CommitWebHook(branchName);
    }

    public static class GitRepository implements Repository {
        private List<WebHook> webHookList = new ArrayList<>();
        private Map<String, List<Commit>> commitsByBranch = new HashMap<>();

        @Override
        public void addWebHook(WebHook webHook) {
            webHookList.add(webHook);
            logger.info("WebHookLists: " + webHookList);
        }

        @Override
        public Commit commit(String branch, String author, String[] changes) {
            Commit commit = new Commit(author, changes);

            Event newEvent = new Event(Event.Type.COMMIT, branch, List.of(commit));
            logger.info("newEvent: " + newEvent);

            if (webHookList != null && !webHookList.isEmpty()) {
                commitsByBranch.computeIfAbsent(branch, k -> new ArrayList<>()).add(commit);
                for (int i = 0; i < webHookList.size(); i++) {
                    WebHook temp = webHookList.get(i);
                    if (temp.type().equals(Event.Type.COMMIT) && temp.branch().equals(branch)) {
                        webHookList.get(i).onEvent(newEvent);

                        logger.info("Updated/Notified webHook: " + webHookList.get(i) + ", and coughtEvents: "
                                + webHookList.get(i).caughtEvents());
                        break;
                    }
                }
            }
            return commit;

        }

        @Override
        public void merge(String sourceBranch, String targetBranch) {

            List<WebHook> hooks = new ArrayList<>();
            if (webHookList != null && !webHookList.isEmpty()) {

                List<Commit> sourceCommits = commitsByBranch.get(sourceBranch);

                for (int i = 0; i < webHookList.size(); i++) {
                    WebHook temp = webHookList.get(i);
                    if (temp.type().equals(Event.Type.MERGE) && temp.branch().equals(targetBranch)) {
                        logger.info("From: " + sourceBranch + ", TO: " + targetBranch + " MERGE");
                        logger.info("Writing: " + sourceCommits);
                        hooks.add(temp);
                        Event event = new Event(Event.Type.MERGE, targetBranch, sourceCommits);
                        if (!temp.caughtEvents().equals(List.of(event))) {
                            hooks.forEach(hook -> hook.onEvent(event));
                        }
                    }
                }

            }

        }


    }

    private static class MergeWebHook implements WebHook {
        private String branch;
        private Event.Type type;
        private List<Event> caughtEvents;

        MergeWebHook(String branch) {
            this.branch = branch;
            this.type = Event.Type.MERGE;
            this.caughtEvents = new ArrayList<>();
        }

        @Override
        public String branch() {
            return branch;
        }

        @Override
        public Event.Type type() {
            return type;
        }

        @Override
        public List<Event> caughtEvents() {
            return caughtEvents;
        }

        @Override
        public void onEvent(Event event) {
            caughtEvents.add(event);
        }
    }

    private static class CommitWebHook implements WebHook {
        private String branch;
        private Event.Type type;
        private List<Event> caughtEvents = new ArrayList<>();

        CommitWebHook(String branch) {
            this.branch = branch;
            this.type = Event.Type.COMMIT;
            this.caughtEvents = new ArrayList<>();
        }

        @Override
        public String branch() {
            return branch;
        }

        @Override
        public Event.Type type() {
            return type;
        }

        @Override
        public List<Event> caughtEvents() {
            return caughtEvents;
        }

        @Override
        public void onEvent(Event event) {
            caughtEvents.add(event);
        }
    }


}
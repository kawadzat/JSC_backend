package io.getarrays.securecapita.roles.prerunner;

public enum AUTH_ROLE {
    USER,
    ADMIN,
    PRINCIPAL_ADMIN,
    DEPUTY_HEAD,
    HEAD_ADMIN,
    SYSADMIN;

    public static int getPriority(AUTH_ROLE authRole) {
        switch (authRole) {
            case USER -> {
                return 0;
            }
            case ADMIN -> {
                return 1;
            }
            case PRINCIPAL_ADMIN -> {
                return 2;
            }
            case DEPUTY_HEAD, SYSADMIN -> {
                return 3;
            }
        }
        return 0;
    }

    public static int getMaxPriority() {
        return 3;
    }
}
//Initiation/Creation - by Administration Officer
//Approval - by Principal Administration Officer
//Confirmation - by Deputy Head of Administration
//Authorization - by Head of Administration
//Submission to PMU - by Head of Administration
//Completion - by Initiator (on receiving goods)
//if we change you have to edit in frontend,ok
//ok?yes
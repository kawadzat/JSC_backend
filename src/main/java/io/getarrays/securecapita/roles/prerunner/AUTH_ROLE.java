package io.getarrays.securecapita.roles.prerunner;

public enum AUTH_ROLE {
    USER,
    DEPUTY_REGISTRAR,
    REGISTRAR,
    ADMIN,
    PRINCIPAL_ADMIN,
    HEAD_ADMIN,
    SYSADMIN,
    AUDITOR,

    SECRETARY;



    public static int getPriority(AUTH_ROLE authRole) {
        switch (authRole) {
            case USER -> {
                return 0;
            }
            case DEPUTY_REGISTRAR ,REGISTRAR -> {
                return 1;
            }
            case ADMIN -> {
                return 2;
            }
            case PRINCIPAL_ADMIN -> {
                return 3;
            }
            case HEAD_ADMIN, SYSADMIN,AUDITOR -> {
                return 4;
            }

            case SECRETARY ->  {
                return 5;
            }

        }
        return 0;
    }

    public static int getMaxPriority() {
        return 5;
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
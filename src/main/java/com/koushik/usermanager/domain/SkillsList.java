package com.koushik.usermanager.domain;

public enum SkillsList {
    FRONTEND_DEVELOPMENT,
    JAVA_SCRIPT,
    REACT_JS,
    ANGULAR_JS,
    BACKEND_DEVELOPER,
    JAVA,
    C_PLUS_PLUS{
        @Override
        public String toString() {
            return "C++";
        }
    },
    C_SHARP{
        @Override
        public String toString() {
            return "C#";
        }
    },
    PYTHON,
    SPRING,
    MAVEN,
    MICROSOFT_WORD,
    MICROSOFT_POWERPOINT,
    RELATIONAL_DATABASES,
    NON_RELATIONAL_DATABASES,
    MY_SQL,
}

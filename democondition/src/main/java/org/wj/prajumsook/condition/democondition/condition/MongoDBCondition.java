package org.wj.prajumsook.condition.democondition.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MongoDBCondition implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String type = conditionContext.getEnvironment().getProperty("db_type");
        return (type != null && type.equalsIgnoreCase("mongo"));
    }
}

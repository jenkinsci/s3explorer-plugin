package com.finc.s3explorer;

import java.util.Collection;
import java.util.Collections;

import hudson.Extension;
import hudson.model.AbstractProject;
import hudson.model.Action;
import hudson.model.TransientProjectActionFactory;

@Extension
public class S3ExplorerProjectActionFactory extends TransientProjectActionFactory {

    @Override
    public Collection<? extends Action> createFor(AbstractProject abstractProject) {

        return Collections.singletonList(new S3ExplorerAction(abstractProject));
    }
}


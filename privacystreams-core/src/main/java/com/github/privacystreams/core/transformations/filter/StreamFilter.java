package com.github.privacystreams.core.transformations.filter;

import com.github.privacystreams.core.Item;
import com.github.privacystreams.core.transformations.PStreamTransformation;

/**
 * Exclude some items from PStream
 */

abstract class StreamFilter extends PStreamTransformation {

    @Override
    protected void onInput(Item item) {
        if (item.isEndOfStream()) {
            this.finish();
            return;
        }
        if (this.keep(item)) this.output(item);
    }

    protected abstract boolean keep(Item item);
}

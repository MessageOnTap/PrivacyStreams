package com.github.privacystreams.core.transformations.limit;

import com.github.privacystreams.core.Item;
import com.github.privacystreams.core.transformations.PStreamTransformation;

/**
 * Limit the length of the stream.
 */
abstract class StreamLimiter extends PStreamTransformation {

    @Override
    protected void onInput(Item item) {
        if (item.isEndOfStream()) {
            this.finish();
            return;
        }
        if (this.keep(item)) this.output(item);
        else this.finish();
    }

    protected abstract boolean keep(Item item);
}

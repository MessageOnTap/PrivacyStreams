package io.github.privacystreams.communication.emailinfo;

/**
 * Created by xiaobing1117 on 2017/8/25.
 */

public interface EmailAccountNameListener {
    void onSuccess(String name);
    void onFail();
}

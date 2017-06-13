package interview;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Created by shikeyue on 17/6/13.
 */
public class NonblockingTemplate {

    public static class IntendedModification {
        public AtomicBoolean completed = new AtomicBoolean(false);
    }

    private AtomicStampedReference<IntendedModification> ongoingMod = new AtomicStampedReference<>(null, 0);


    public void modify() {
        while(!attemptModifyASR());
    }

    private boolean attemptModifyASR() {

        boolean modified = false;

        IntendedModification currentOngoingMod = ongoingMod.getReference();

        int stamp = ongoingMod.getStamp();

        if (currentOngoingMod == null) {
            IntendedModification newMod = new IntendedModification();

            boolean modSubmitted = ongoingMod.compareAndSet(null, newMod, stamp, stamp + 1);

            if (modSubmitted) {
                modified = true;
            }
        } else {
            modified = false;
        }

        return modified;

    }


}

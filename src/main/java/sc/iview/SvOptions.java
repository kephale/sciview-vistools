package sc.iview;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * SciView options.
 * Modeled after BigDataViewer's BdvOptions. One difference is that the counterpart of BdvOptions' Values are embedded
 *   in a SciView instance. However, a HashMap of metadata can still be used prior to the instantiation of a SciView.
 */
public class SvOptions {

    public final Values values = new Values();

    /**
     * Default options should lead to sensible behavior...
     */
    public SvOptions() {

    }

    public SciView getAddTo() {
        return values.addTo.apply(values);
    }

    /**
     * Values is automatically initialized, therefore we want all constructor code to be lazy (e.g. Supplier's etc.)
     */
    public static class Values {

        private final Function<Values, SciView> addTo;
        private Map<String, Object> metadata = new HashMap<>();

        Values() {
            // Could prepopulate metadata with key info, like version

            addTo = this::defaultAddTo;
        }

        private final SciView defaultAddTo(Values values) {
            Map<String, Object> m = defaultMetadata();

            m.putAll(values.metadata);
            values.metadata = m;

            return sciViewFromValues(values);
        }

        private static final Map<String, Object> defaultMetadata() {
            Map<String, Object> m = new HashMap<>();
            m.put("sc.iview.CenterOnNewNodes", true);
            m.put("sc.iview.BlockOnNewNodes", true);
            return m;
        }
    }

    /**
     * Create
     * @param values
     * @return
     */
    private static SciView sciViewFromValues(Values values) {
        SciView sciView;
        try {
            sciView = SciView.create();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        sciView.waitForSceneInitialisation();

        // Process metadata hashmap, perhaps even save all relevant config (incl scenery settings) to help debug logs
        for( String key : values.metadata.keySet() ) {
            Object val = values.metadata.get(key);

            System.out.println("md: " + key + " " + val);

            if( key.compareTo("sc.iview.CenterOnNewNodes") == 0 ) {
                sciView.setCenterOnNewNodes( (boolean) val );
            } else if( key.compareTo("sc.iview.BlockOnNewNodes") == 0 ) {
                sciView.setBlockOnNewNodes( (boolean) val );
            }
        }

        return sciView;
    }

}

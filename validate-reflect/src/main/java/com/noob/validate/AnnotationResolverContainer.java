package com.noob.validate;

import java.util.HashMap;
import java.util.Map;

import com.noob.validate.abstracts.IResolver;
import com.noob.validate.annotation.Decimals;
import com.noob.validate.annotation.LcRcRange;
import com.noob.validate.annotation.Length;
import com.noob.validate.annotation.LoRoRange;
import com.noob.validate.annotation.Multiple;
import com.noob.validate.annotation.NotBlank;
import com.noob.validate.annotation.NotNull;
import com.noob.validate.annotation.StrMatchDate;
import com.noob.validate.annotation.resolver.DecimalsResolver;
import com.noob.validate.annotation.resolver.LcRcRangeResolver;
import com.noob.validate.annotation.resolver.LengthResolver;
import com.noob.validate.annotation.resolver.LoRoRangeResolver;
import com.noob.validate.annotation.resolver.MultipleResolver;
import com.noob.validate.annotation.resolver.NotBlankResolver;
import com.noob.validate.annotation.resolver.NotNullResolver;
import com.noob.validate.annotation.resolver.StrMatchDateResolver;

public class AnnotationResolverContainer {

    private static Map<Class<?>, IResolver> container = new HashMap<Class<?>, IResolver>() {

        private static final long serialVersionUID = -5060019955315988615L;

        {
            put(NotBlank.class, new NotBlankResolver());
            put(Length.class, new LengthResolver());
            put(NotNull.class, new NotNullResolver());
            put(StrMatchDate.class, new StrMatchDateResolver());
            put(LcRcRange.class, new LcRcRangeResolver());
            put(LoRoRange.class, new LoRoRangeResolver());
            put(Multiple.class, new MultipleResolver());
            put(Decimals.class, new DecimalsResolver());

        }
    };

    public static Map<Class<?>, IResolver> getContainer() {
        return container;
    }

}
